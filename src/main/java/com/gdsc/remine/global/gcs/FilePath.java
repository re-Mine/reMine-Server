package com.gdsc.remine.global.gcs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FilePath {
    DECLARATION("declaration");

    private final String path;

    public String getFilePath() {
        return this.path;
    }
}
