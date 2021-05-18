cmake_minimum_required(VERSION 3.12)
project(lab_03_comp_net)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(lab_03_comp_net
        client.cpp
        HttpCodes.cpp
        HttpCodes.h
        RequestParser.cpp
        RequestParser.h
        ResponseParser.cpp
        ResponseParser.h
        server.cpp
        Statistic.cpp
        Statistic.h
        ThreadPool.cpp
        ThreadPool.hpp
        util.cpp
        util.h)
