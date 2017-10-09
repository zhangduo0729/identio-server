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

package net.identio.server.service.oauth.model;

public class ClientCredentialsRequest {

    private String grantType;
    private String scope;

    public String getGrantType() {
        return grantType;
    }

    public ClientCredentialsRequest setGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public ClientCredentialsRequest setScope(String scope) {
        this.scope = scope;
        return this;
    }
}
