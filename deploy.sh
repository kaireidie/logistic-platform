#!/usr/bin/env bash

set -e

CYAN='\033[0;36m'
YELLOW='\033[1;33m'
GREEN='\033[0;32m'
NC='\033[0m'

echo -e "${CYAN}🚀 Начинаем развертывание Logistic Platform...${NC}"

echo -e "${YELLOW}📦 Проверяем/создаем namespace...${NC}"
kubectl create namespace logistic-platform --dry-run=client -o yaml | kubectl apply -f -

echo -e "${YELLOW}📦 Устанавливаем NGINX Ingress Controller...${NC}"
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

echo -e "${YELLOW}⏳ Ожидаем готовности Ingress Controller...${NC}"
kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=90s

echo -e "${YELLOW}⚙️ Применяем конфигурации приложения...${NC}"
kubectl apply -f k8s/app/

echo -e "${GREEN}✅ Развертывание успешно завершено!${NC}"
echo -e "${CYAN}🔗 Приложение доступно по адресу: http://localhost${NC}"