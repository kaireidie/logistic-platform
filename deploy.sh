#!/usr/bin/env bash

set -e

CLUSTER_NAME="logistic-platform"
NAMESPACE="logistic-platform"
IMAGE="logistic-platform:latest"

CYAN='\033[0;36m'
YELLOW='\033[1;33m'
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

echo -e "${CYAN}🚀 Starting Logistic Platform deployment...${NC}"
echo ""

# ==================================================
# Check required tools
# ==================================================

echo -e "${YELLOW}🔍 Checking required tools...${NC}"

for command in docker kubectl kind; do
    if ! command -v "$command" >/dev/null 2>&1; then
        echo -e "${RED}❌ $command is not installed or not available in PATH.${NC}"
        exit 1
    fi
done

echo -e "${GREEN}✅ Docker, kubectl and Kind are available.${NC}"
echo ""

# ==================================================
# Create Kind cluster
# ==================================================

echo -e "${YELLOW}☸️ Checking Kind cluster...${NC}"

if kind get clusters | grep -qx "$CLUSTER_NAME"; then
    echo -e "${GREEN}✅ Kind cluster '$CLUSTER_NAME' already exists.${NC}"
else
    echo -e "${YELLOW}☸️ Creating Kind cluster...${NC}"

    cat <<EOF | kind create cluster --name "$CLUSTER_NAME" --config=-
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4

name: ${CLUSTER_NAME}

nodes:
  - role: control-plane
    extraPortMappings:
      - containerPort: 80
        hostPort: 80
        protocol: TCP
      - containerPort: 443
        hostPort: 443
        protocol: TCP
EOF

    echo -e "${GREEN}✅ Kind cluster created.${NC}"
fi

echo ""

# ==================================================
# Build Docker image
# ==================================================

echo -e "${YELLOW}🐳 Building Docker image...${NC}"

docker build \
    -t "$IMAGE" \
    .

echo -e "${GREEN}✅ Docker image built.${NC}"
echo ""

# ==================================================
# Load image into Kind
# ==================================================

echo -e "${YELLOW}📦 Loading Docker image into Kind...${NC}"

kind load docker-image \
    "$IMAGE" \
    --name "$CLUSTER_NAME"

echo -e "${GREEN}✅ Docker image loaded into Kind.${NC}"
echo ""

# ==================================================
# Namespace
# ==================================================

echo -e "${YELLOW}📦 Applying namespace...${NC}"

kubectl apply \
    -f k8s/namespace.yaml

echo -e "${GREEN}✅ Namespace configured.${NC}"
echo ""

# ==================================================
# Secret and ConfigMap
# ==================================================

echo -e "${YELLOW}⚙️ Applying application configuration...${NC}"

kubectl apply \
    -f k8s/secret.yaml \
    -n "$NAMESPACE"

kubectl apply \
    -f k8s/configmap.yaml \
    -n "$NAMESPACE"

echo -e "${GREEN}✅ Configuration applied.${NC}"
echo ""

# ==================================================
# NGINX Ingress Controller
# ==================================================

echo -e "${YELLOW}🌐 Installing NGINX Ingress Controller...${NC}"

kubectl apply \
    -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

echo -e "${YELLOW}⏳ Waiting for NGINX Ingress Controller...${NC}"

kubectl wait \
    --namespace ingress-nginx \
    --for=condition=ready pod \
    --selector=app.kubernetes.io/component=controller \
    --timeout=180s

echo -e "${GREEN}✅ NGINX Ingress Controller is ready.${NC}"
echo ""

# ==================================================
# PostgreSQL
# ==================================================

echo -e "${YELLOW}🐘 Deploying PostgreSQL...${NC}"

kubectl apply \
    -f k8s/postgres/service.yaml \
    -n "$NAMESPACE"

kubectl apply \
    -f k8s/postgres/statefulset.yaml \
    -n "$NAMESPACE"

echo -e "${YELLOW}⏳ Waiting 10 seconds for PostgreSQL pod to start...${NC}"

sleep 10

echo -e "${YELLOW}⏳ Waiting for PostgreSQL to become ready...${NC}"

kubectl wait \
    --for=condition=ready \
    pod/postgres-0 \
    -n "$NAMESPACE" \
    --timeout=180s

echo -e "${GREEN}✅ PostgreSQL is ready.${NC}"
echo ""

# ==================================================
# Application
# ==================================================

echo -e "${YELLOW}☕ Deploying Logistic Platform...${NC}"

kubectl apply \
    -f k8s/app/service.yaml \
    -n "$NAMESPACE"

kubectl apply \
    -f k8s/app/deployment.yaml \
    -n "$NAMESPACE"

echo -e "${YELLOW}⏳ Waiting for application...${NC}"

kubectl rollout status \
    deployment/logistic-platform \
    -n "$NAMESPACE" \
    --timeout=180s

echo -e "${GREEN}✅ Logistic Platform is ready.${NC}"
echo ""

# ==================================================
# Ingress
# ==================================================

echo -e "${YELLOW}🌐 Applying application Ingress...${NC}"

kubectl apply \
    -f k8s/app/ingress.yaml \
    -n "$NAMESPACE"

echo -e "${GREEN}✅ Ingress configured.${NC}"
echo ""

# ==================================================
# Final status
# ==================================================

echo -e "${GREEN}==============================================${NC}"
echo -e "${GREEN}✅ Logistic Platform deployed successfully!${NC}"
echo -e "${GREEN}==============================================${NC}"
echo ""

echo -e "${CYAN}🔗 Application:${NC} http://localhost"
echo -e "${CYAN}❤️  Health:${NC}      http://localhost/actuator/health"
echo ""

echo -e "${YELLOW}📊 Kubernetes status:${NC}"

kubectl get pods,svc,ingress \
    -n "$NAMESPACE"