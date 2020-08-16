package com.codefresh.api.models;

public class PipelineRequest {

    private String kind;
    private Metadata metadata;
    private Spec spec;
    private String version;

    public Metadata getMetadata() {
        return metadata;
    }

    public Spec getSpec() {
        return spec;
    }

    public String getKind() {
        return kind;
    }

    public String getVersion() {
        return version;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
