RELEASE=0.0.2

all: help

help:
	@echo "Usage:"
	@echo "	  make release  - generate tar.gz files for sourceforge release"
	@echo "	  make clean    - clean up tar.gz files"

release:
	git archive master | gzip >  hicloud-hadoop-${RELEASE}.tar.gz
clean:
	rm hicloud-hadoop-${RELEASE}.tar.gz
