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

package net.identio.server.service.orchestration.model;

public class OrchestrationErrorStatus {

    public static final String SERVER_ERROR = "server.error";
    public static final String AUTH_METHOD_UNKNOWN = "auth.method.unknown";
    public static final String AUTH_METHOD_NOT_ALLOWED = "auth.method.not.allowed";
    public static final String INVALID_TRANSACTION = "invalid.transaction";
    public static final String INVALID_SCOPE = "invalid.scope";
}
