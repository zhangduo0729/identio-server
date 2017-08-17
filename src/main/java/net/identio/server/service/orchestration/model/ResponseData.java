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

public class ResponseData {

    private String url;
    private String data;
    private String relayState;

    public String getUrl() {
        return url;
    }

    public ResponseData setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getData() {
        return data;
    }

    public ResponseData setData(String data) {
        this.data = data;
        return this;
    }

    public String getRelayState() {
        return relayState;
    }

    public ResponseData setRelayState(String relayState) {
        this.relayState = relayState;
        return this;
    }
}
