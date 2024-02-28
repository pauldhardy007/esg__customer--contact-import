DOCKER_PROJECT_MODIFICATOR := $(shell echo ${BRANCH_NAME} | sed 's@origin/@@' | sed 's@/@_@' | awk '{print tolower($$0)}')
DOCKER_COMPOSE_OPTS ?= --project-name ${NAME}${DOCKER_PROJECT_MODIFICATOR}

image: tardget

DOCKER_COMPOSE ?= IMAGE=${IMAGE} USER_ID=${USER_ID} docker-compose ${DOCKER_COMPOSE_OPT} -f ./docker/docker-compose.build.yml

run:
	${DOCKER_COMPOSE} -f ./docker/docker-compose.yml -f ./docker/docker-compose.services.yml up