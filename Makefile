run-distr :
	make -C app run-distr

run-help :
	make -C app run-help

run-install-dir :
	make -C app run-install-dir

build :
	make -C app build

run :
	make -C app run

checkstyle-main :
	make -C app checkstyle-main

report:
	make -C app report

.PHONY: build