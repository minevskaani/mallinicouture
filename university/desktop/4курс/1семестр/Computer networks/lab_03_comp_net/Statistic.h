#ifndef LAB_03_HTTP_SERVER_STATISTIC_H
#define LAB_03_HTTP_SERVER_STATISTIC_H

#include <map>
#include <set>
using namespace std;
// Сохранять информацию обо всех посещенных пользователем страницах с указанием имени/идентификатора пользователя

class Statistic {
public:
    void add(const string& identifier, const string& path);
    string str() const;
private:
    map<string, set<string>> userStatistic;
};

#endif
