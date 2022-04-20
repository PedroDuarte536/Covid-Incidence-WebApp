trap stop SIGINT

stop() {
  docker-compose -f docker-compose.base.yml -f docker-compose.dev.yml down --volumes
}

docker-compose -f docker-compose.base.yml -f docker-compose.dev.yml up