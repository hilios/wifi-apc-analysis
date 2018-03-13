cwd := $(shell pwd)

default:
	docker run --user root --rm -it -p 8888:8888 -v $(cwd):/home/jovyan jupyter/minimal-notebook start-notebook.sh --NotebookApp.token=''
