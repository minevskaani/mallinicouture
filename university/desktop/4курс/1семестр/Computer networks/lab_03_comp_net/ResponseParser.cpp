#include "ResponseParser.h"

#include "HttpCodes.h"
#include "util.h"
#include <vector>
#include <sstream>

int ResponseParser::parse(const string &raw) {
    clear();
    vector<string> lines = split_until_empty(raw, "\r\n");
    if (lines[0].empty()) return -1;

    vector<string> firstLineTokens = split(lines[0], " ");
    if (firstLineTokens.size() < 3) return -1;

    // protocol
    if (firstLineTokens[0].compare(0, 4, "HTTP")) {
        return -1;
    }
    this->protocol.assign(firstLineTokens[0]);

    // status code
    if (firstLineTokens[1].empty() || sscanf(firstLineTokens[1].c_str(), "%d", &this->status.first) != 1) {
        return -1;
    }

    // status msg
    stringstream statusMsgS;
    for (int i = 2; i < firstLineTokens.size(); i++) {
        statusMsgS << firstLineTokens[i];
        if (i + 1 != firstLineTokens.size()) {
            statusMsgS << " ";
        }
    }
    string statusMsg = statusMsgS.str();
    if (statusMsg.empty()) {
        return -1;
    }
    this->status.second.assign(statusMsg);

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

const map<string, string> &ResponseParser::getHeaders() const {
    return headers;
}

void ResponseParser::setHeaders(const map<string, string> &headers) {
    ResponseParser::headers = headers;
}

const string &ResponseParser::getBody() const {
    return body;
}

void ResponseParser::setBody(const string &body) {
    ResponseParser::body = body;
}
string ResponseParser::getHeadersStr() const {
    stringstream res;
    res << protocol << " " << status.first << " " << status.second << "\r\n";

    for (const auto& header : headers) {
        res << header.first << ": " << header.second << "\r\n";
    }
    res << "\r\n";

    return res.str();
}

string ResponseParser::str() const {
    string res(getHeadersStr());
    res.append(body);

    return res;
}

void ResponseParser::clear() {
    headers.clear();
    status.first = 0;
    status.second = "";
    protocol = "HTTP/1.1";
    headers.clear();
    body.clear();
}

void ResponseParser::updateDate() {
    headers[H_DATE] = getDate();
}

const string &ResponseParser::getAProtocol() const {
    return protocol;
}

void ResponseParser::setAProtocol(const string &aProtocol) {
    protocol = aProtocol;
}

const pair<int, string> &ResponseParser::getStatus() const {
    return status;
}

void ResponseParser::setStatus(const pair<int, string> &status) {
    ResponseParser::status = status;
}

void ResponseParser::fromError(int statusCode) {
    clear();
    this->status.first = statusCode;
    this->status.second = HttpStatus_reasonPhrase(statusCode);
    headers[H_CONNECTION] = "close";
    updateDate();
}
