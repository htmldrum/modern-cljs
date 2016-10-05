# Chaining source compilation together with fs watching
connect_repl:
	boot repl -c
watch:
	boot serve -d target watch reload cljs-repl cljs target -d target
compile:
	boot cljs target -d target
run:
	boot wait serve -d target
.PHONY:
	compile run
