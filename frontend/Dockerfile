FROM node:20-alpine

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

EXPOSE 3000

# Обязательно должна быть команда CMD перед npm run dev!
CMD ["npm", "run", "dev"]