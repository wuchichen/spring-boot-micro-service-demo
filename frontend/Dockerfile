######## Stage 1 ########
FROM node:latest as builder

# Change working directory
WORKDIR /app

# Copy source code
COPY . .

# Install dependencies
RUN npm install

# Build
RUN npm run build

######## Stage 2 ########
FROM nginx:latest

# Clear nginx folder
RUN rm -rf /etc/nginx/conf.d/* && rm -rf /usr/share/nginx/html/*

# Nginx config
COPY default.conf /etc/nginx/conf.d/

# Front end code
COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx","-g","daemon off;"]
