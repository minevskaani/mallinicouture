all: *.cpp *.h
	g++ util.cpp util.h Statistic.cpp Statistic.h server.cpp RequestParser.cpp RequestParser.h ThreadPool.cpp ThreadPool.hpp ResponseParser.cpp ResponseParser.h HttpCodes.cpp HttpCodes.h -o server.out -lpthread
	g++ client.cpp ResponseParser.cpp ResponseParser.h RequestParser.cpp RequestParser.h util.cpp util.h HttpCodes.cpp HttpCodes.h -o client.out

clean:
	$(RM) *.out