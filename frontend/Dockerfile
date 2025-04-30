FROM node:20-alpine

WORKDIR /app

COPY package.json package-lock.json ./

COPY . .

RUN npm install -g npm@latest

RUN npm install 

RUN npm run build

FROM nginx:alpine

COPY --from=0 /app/dist /usr/share/nginx/html

COPY nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]