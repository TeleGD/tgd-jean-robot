build:
	mkdir -p bin
	javac -d bin -cp src:res:lib/* src/fr/game/Game.java

run:
	java -cp bin:res:lib/* -Djava.library.path=res/natives fr.game.Game

clean:
	rm -r -f bin/*

.PHONY: build run clean
