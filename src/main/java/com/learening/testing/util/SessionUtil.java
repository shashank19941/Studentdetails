package com.learening.testing.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("token") != null;
    }
}

