#ifndef LAB_03_HTTP_SERVER_UTIL_H
#define LAB_03_HTTP_SERVER_UTIL_H

#include <string>
#include <vector>

using namespace std;

vector<string> split(const string& str, const string& delim);
/**
 * Splits a string into tokens. After first empty token stop splitting and add remaining text into vector
 * @param s
 * @param sep
 * @return
 */
vector<string> split_until_empty(const string& str, const string& delim);

string getDate();

#endif //LAB_03_HTTP_SERVER_UTIL_H
