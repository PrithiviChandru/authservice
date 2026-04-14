package com.micro.authservice.security;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlackListService {
    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();

    public void blacklistToken(String accessToken) {
        blacklist.add(accessToken);
    }

    public boolean isBlacklisted(String accessToken) {
        return blacklist.contains(accessToken);
    }
}
