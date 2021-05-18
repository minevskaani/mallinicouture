#ifndef LAB_03_HTTP_SERVER_RESPONSEPARSER_H
#define LAB_03_HTTP_SERVER_RESPONSEPARSER_H

#include <string>
#include <map>

using namespace std;

class ResponseParser {
public:
    /**
     * Parse a raw message
     *
     * @param raw msg to be parsed
     * @return http status code (200 if no errors)
     */
    int parse(const string &raw);
    void fromError(int statusCode);

    void updateDate();

    const map<string, string> &getHeaders() const;

    void setHeaders(const map<string, string> &headers);

    const string &getBody() const;

    void setBody(const string &body);

    const string &getAProtocol() const;

    void setAProtocol(const string &aProtocol);

    const pair<int, string> &getStatus() const;

    void setStatus(const pair<int, string> &status);

    string getHeadersStr() const;

    string str() const;

    void clear();
private:
    map<string, string> headers;
    string protocol = "HTTP/1.1";
    pair<int, string> status;

    string body;
};


#endif //LAB_03_HTTP_SERVER_RESPONSEPARSER_H
