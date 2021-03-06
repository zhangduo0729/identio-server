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
package net.identio.server.service.authentication.saml;

import net.identio.server.model.AuthLevel;

import java.util.HashMap;

public class SamlAuthMap {

    private HashMap<String, AuthLevel> in;
    private HashMap<AuthLevel, String> out;

    public HashMap<String, AuthLevel> getIn() {
        return in;
    }

    public void setIn(HashMap<String, AuthLevel> in) {
        this.in = in;
    }

    public HashMap<AuthLevel, String> getOut() {
        return out;
    }

    public void setOut(HashMap<AuthLevel, String> out) {
        this.out = out;
    }

}
