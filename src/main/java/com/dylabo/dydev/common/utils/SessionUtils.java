package com.dylabo.dydev.common.utils;

import com.dylabo.core.common.utils.CommonRequestUtils;
import com.dylabo.dydev.common.session.SessionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {

    private SessionUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String SESSION_ATTRIBUTE = "session";

    public static SessionDto getSession() {
        HttpServletRequest request = CommonRequestUtils.getRequest();

        HttpSession session = request.getSession();

        return (SessionDto) session.getAttribute(SESSION_ATTRIBUTE);
    }

    public static SessionDto setSession(SessionDto sessionDto) {
        HttpServletRequest request = CommonRequestUtils.getRequest();

        HttpSession session = request.getSession();

        session.setAttribute(SESSION_ATTRIBUTE, sessionDto);
        session.setMaxInactiveInterval(60 * 30); // 30분

        return sessionDto;
    }

    public static void clearSession() {
        HttpServletRequest request = CommonRequestUtils.getRequest();

        HttpSession session = request.getSession();

        session.setAttribute(SESSION_ATTRIBUTE, null);
    }

}
