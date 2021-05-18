#include <sstream>
#include <string>
#include <numeric>
#include "Statistic.h"

void Statistic::add(const string& identifier, const string& path) {
    userStatistic[identifier].insert(path);
}

string Statistic::str() const {
    stringstream resStream;
    auto space_fold = [](std::string a, std::string b) {
        return std::move(a) + ' ' + std::move(b);
    };
    for (const auto& element : userStatistic) {
        string pages = accumulate(element.second.begin(), element.second.end(), string(""), space_fold);

        resStream << element.first << ": " << pages << endl;
    }
    resStream << endl;
    string res = resStream.str();
    if (res.length() == 0) return "Empty";

    return resStream.str();
}
