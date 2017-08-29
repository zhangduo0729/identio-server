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

import net.identio.server.mvc.oauth.model.ConsentRequest;
import net.identio.server.mvc.oauth.model.ConsentResponse;
import net.identio.server.service.orchestration.exceptions.WebSecurityException;
import net.identio.server.model.AuthorizationScope;
import net.identio.server.service.orchestration.model.OrchestrationErrorStatus;
import net.identio.server.service.orchestration.model.ResponseData;
import net.identio.server.service.transaction.model.TransactionData;
import net.identio.server.mvc.oauth.model.ConsentContext;
import net.identio.server.service.transaction.TransactionService;
import net.identio.server.service.transaction.model.TransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsentService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OAuthService oAuthService;

    public ConsentContext getConsentContext(String transactionId, String sessionId)
            throws WebSecurityException {

        TransactionData transactionData = getTransactionData(transactionId, sessionId);

        List<AuthorizationScope> authorizedScopes = transactionData
                .getRequestParsingInfo()
                .getRequestedScopes().values().stream()
                .map(AuthorizationScope::getPublicCopy)
                .collect(Collectors.toList());

        return new ConsentContext().setRequestedScopes(authorizedScopes)
                .setAudience(transactionData.getRequestParsingInfo().getSourceApplicationName());

    }

    public ConsentResponse validateConsent(ConsentRequest consentRequest, String transactionId, String sessionId) throws WebSecurityException {

        TransactionData transactionData = getTransactionData(transactionId, sessionId);

        // Check that each validated scope is in the requested scopes
        for (String scopeName : consentRequest.getApprovedScopes()) {
            if (!transactionData.getRequestParsingInfo().getRequestedScopes().containsKey(scopeName)) {
                throw new WebSecurityException(OrchestrationErrorStatus.INVALID_SCOPE);
            }
        }

        return new ConsentResponse().setSuccess(true)
                .setResponseData(
                        oAuthService.generateSuccessResponse(transactionData.getRequestParsingInfo(),
                        transactionData.getUserSession())
                );
    }

    private TransactionData getTransactionData(String transactionId, String sessionId) throws WebSecurityException {

        TransactionData transactionData = transactionService.getTransaction(sessionId, transactionId);

        // Check that we are in the correct transaction state
        if (transactionData.getState() != TransactionState.CONSENT) {
            transactionService.removeTransactionData(transactionData);
            throw new WebSecurityException(OrchestrationErrorStatus.INVALID_TRANSACTION);
        }

        return transactionData;
    }
}
