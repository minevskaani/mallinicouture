#include "util.h"

vector<string> split(const string& str, const string& delim)
{
    vector<string> tokens;
    size_t prev = 0, pos;
    do {
        pos = str.find(delim, prev);
        if (pos == string::npos) pos = str.length(); // if error
        string token = str.substr(prev, pos-prev);
        //if (!token.empty()) { // we need empty strings
            tokens.push_back(token);
        //}
        prev = pos + delim.length();
    } while (pos < str.length() && prev < str.length());

    return tokens;
}

vector<string> split_until_empty(const string& str, const string& delim)
{
    vector<string> tokens;
    size_t prev = 0, pos;
    do {
        pos = str.find(delim, prev);
        if (pos == string::npos) pos = str.length(); // if error
        string token = str.substr(prev, pos - prev);
        tokens.push_back(token);
        prev = pos + delim.length();
        if (token.empty()) {
            tokens.push_back(str.substr(prev, str.length() - prev));
            break;
        }
    } while (pos < str.length() && prev < str.length());
    return tokens;
}

string getDate() {
    char buf[50];
    time_t now = time(0);
    struct tm tm = *gmtime(&now);
    strftime(buf, sizeof buf, "%a, %d %b %Y %H:%M:%S %Z", &tm);

    return buf;
}
