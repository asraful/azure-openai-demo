FROM nginx

# Copy config nginx
COPY ./nginx.conf /etc/nginx/nginx.conf

WORKDIR /usr/share/nginx/html

# Containers run nginx with global directives and daemon off
ENTRYPOINT ["nginx", "-g", "daemon off;"]