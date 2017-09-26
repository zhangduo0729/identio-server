/*
 * This file is part of Ident.io.
 *
 * Ident.io - A flexible authentication server
 * Copyright (c) 2017 Loeiz TANGUY
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package net.identio.server.service.oauth;

import net.identio.server.service.oauth.infrastructure.AuthorizationCodeRepository;
import net.identio.server.service.oauth.infrastructure.OAuthClientRepository;
import net.identio.server.service.oauth.infrastructure.exceptions.AuthorizationCodeFetchException;
import net.identio.server.service.oauth.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class OAuthTokenServiceTest {

    @Mock
    private OAuthClientRepository clientRepository;

    @Mock
    private AuthorizationCodeRepository authorizationCodeRepository;

    @InjectMocks
    private OAuthTokenService oAuthTokenService = new OAuthTokenService();

    @Test
    public void clientGrantNotAuthorized() {

        AuthorizationRequest request = new AuthorizationRequest()
                .setGrantType(OAuthGrants.AUTHORIZATION_CODE).setCode("1234").setRedirectUri("http://example.com/cb");

        OAuthClient client = new OAuthClient();
        client.setClientId("test");
        client.setAllowedGrants(Collections.singletonList("implicit"));
        client.setClientSecret("test");

        // Mockito expectations
        when(clientRepository.getOAuthClientbyId(anyString())).thenReturn(client);

        ValidateTokenResult result = oAuthTokenService.validateTokenRequest(request, "Basic dGVzdDp0ZXN0"); // test: test

        assertEquals(ValidateTokenStatus.FAIL, result.getStatus());
        assertEquals(OAuthErrors.UNAUTHORIZED_CLIENT, result.getErrorStatus());
    }

    @Test
    public void authorizationCodeRepository_getAuthorizationCodeByValue_throws() throws AuthorizationCodeFetchException {

        AuthorizationRequest request = new AuthorizationRequest()
                .setGrantType(OAuthGrants.AUTHORIZATION_CODE).setCode("1234").setRedirectUri("http://example.com/cb");

        OAuthClient client = new OAuthClient();
        client.setClientId("test");
        client.setAllowedGrants(Collections.singletonList("authorization_code"));
        client.setClientSecret("test");

        // Mockito expectations
        when(clientRepository.getOAuthClientbyId(anyString())).thenReturn(client);
        doThrow(AuthorizationCodeFetchException.class).when(authorizationCodeRepository).getAuthorizationCodeByValue(anyString());

        ValidateTokenResult result = oAuthTokenService.validateTokenRequest(request, "Basic dGVzdDp0ZXN0"); // test: test

        assertEquals(ValidateTokenStatus.SERVER_ERROR, result.getStatus());
    }
}