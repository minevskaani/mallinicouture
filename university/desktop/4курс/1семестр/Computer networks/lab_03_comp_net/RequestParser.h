#ifndef LAB_03_HTTP_SERVER_REQUESTPARSER_H
#define LAB_03_HTTP_SERVER_REQUESTPARSER_H

#include <string>
#include <map>

using namespace std;

class RequestParser {
public:
    int parse(const string &raw);

    void updateDate();

    const map<string, string> &getHeaders() const;

    void setHeaders(const map<string, string> &headers);

    const string &getBody() const;

    void setBody(const string &body);

    const string &getMethod() const;

    void setMethod(const string &method);

    const string &getPath() const;

    void setPath(const string &path);

    const string &getProtocol() const;

    void setProtocol(const string &aProtocol);

    string getHeadersStr() const;

    string str() const;

    void clear();
private:
    map<string, string> headers;
    string method;
    string path;
    string protocol;

    string body;
};


#endif
