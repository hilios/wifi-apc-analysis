cwd := $(shell pwd)
uid := $(shell id -u ${whoami})
gid := $(shell id -g ${whoami})

envs := -e NB_UID=$(uid) -e NB_GID=$(gid) -e GRANT_SUDO=yes
opts := -p 8888:8888 -v $(cwd):/home/jovyan
start := start-notebook.sh --NotebookApp.token=''

# https://hub.docker.com/r/jupyter/pyspark-notebook/tags/
image := pyspark-notebook:de0cd8011b9e

default:
	docker run --user root --rm -it $(opts) $(envs) jupyter/$(image) $(start)
