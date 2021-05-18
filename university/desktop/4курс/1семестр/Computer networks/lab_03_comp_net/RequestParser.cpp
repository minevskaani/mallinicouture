#include "RequestParser.h"

#include "util.h"
#include <vector>
#include <sstream>
#include "HttpCodes.h"


int RequestParser::parse(const string &raw) {
    clear();
    vector<string> lines = split_until_empty(raw, "\r\n");
    if (lines[0].empty()) return HttpStatus_BadRequest;

    vector<string> firstLineTokens = split(lines[0], " ");
    if (firstLineTokens.size() < 3) return HttpStatus_BadRequest;

    // HTTP method
    if (firstLineTokens[0].compare(0, 3, "GET")) {
        return HttpStatus_MethodNotAllowed;
    }
    this->method.assign(firstLineTokens[0]);

    // path
    if (firstLineTokens[1].empty() || firstLineTokens[1][0] != '/') {
        return HttpStatus_BadRequest;
    }
    this->path.assign(firstLineTokens[1]);

    // protocol
    if (firstLineTokens[2].empty() || firstLineTokens[2].compare(0, 8, "HTTP/1.1")) {
        return HttpStatus_HTTPVersionNotSupported;
    }
    this->protocol.assign(firstLineTokens[2]);

    bool haveBody = false;
    for (int i = 1; i < lines.size() && !haveBody; i++) {
        if (lines[i].empty()) {
            haveBody = true;
        } else {
            vector<string> headerTokens = split(lines[i], ": ");
            if (headerTokens.size() != 2 || headerTokens[0].empty() || headerTokens[1].empty()) {
                return HttpStatus_BadRequest;
            }

            headers[headerTokens[0]] = headerTokens[1];
        }
    }

    if (haveBody) {
        this->body.assign(lines[lines.size() - 1]);
    }

    return HttpStatus_OK;
}

const map<string, string> &RequestParser::getHeaders() const {
    return headers;
}

void RequestParser::setHeaders(const map<string, string> &headers) {
    RequestParser::headers = headers;
}

const string &RequestParser::getBody() const {
    return body;
}

void RequestParser::setBody(const string &body) {
    RequestParser::body = body;
}
string RequestParser::getHeadersStr() const {
    stringstream res;
    res << method << " " << path << " " << protocol << "\r\n";

    for (const auto& header : headers) {
        res << header.first << ": " << header.second << "\r\n";
    }
    res << "\r\n";

    return res.str();
}

string RequestParser::str() const {
    string res(getHeadersStr());
    res.append(body);

    return res;
}

void RequestParser::clear() {
    method.clear();
    path.clear();
    protocol.clear();
    headers.clear();
    body.clear();
}

void RequestParser::updateDate() {
    headers[H_DATE] = getDate();
}

const string &RequestParser::getMethod() const {
    return method;
}

void RequestParser::setMethod(const string &method) {
    RequestParser::method = method;
}

const string &RequestParser::getPath() const {
    return path;
}

void RequestParser::setPath(const string &path) {
    RequestParser::path = path;
}

const string &RequestParser::getProtocol() const {
    return protocol;
}

void RequestParser::setProtocol(const string &protocol) {
    RequestParser::protocol = protocol;
}
