package com.dylabo.dydev.common.session;

import com.dylabo.core.common.utils.CommonObjectUtils;
import com.dylabo.core.common.utils.CommonRequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SessionComponent {

    private static final String SESSION_ATTRIBUTE = "session";

    public SessionDto getSession() {
        HttpServletRequest request = CommonRequestUtils.getRequest();

        HttpSession session = request.getSession(false);

        if (CommonObjectUtils.nonNull(session)) {
            return (SessionDto) session.getAttribute(SESSION_ATTRIBUTE);
        }

        return null;
    }

    public SessionDto setSession(SessionDto sessionDto) {
        HttpServletRequest request = CommonRequestUtils.getRequest();

        HttpSession session = request.getSession();

        session.setAttribute(SESSION_ATTRIBUTE, sessionDto);
        session.setMaxInactiveInterval(60 * 30); // 30분

        return sessionDto;
    }

    public void setSessionAttribute(String key, Object value) {
        HttpServletRequest request = CommonRequestUtils.getRequest();

        HttpSession session = request.getSession();

        session.setAttribute(key, value);
    }

    public void clearSession() {
        HttpServletRequest request = CommonRequestUtils.getRequest();

        HttpSession session = request.getSession(false);

        if (CommonObjectUtils.nonNull(session)) {
            session.setAttribute(SESSION_ATTRIBUTE, null);
            session.invalidate();
        }
    }

}
