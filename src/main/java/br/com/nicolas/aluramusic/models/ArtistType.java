package br.com.nicolas.aluramusic.models;

public enum ArtistType {
    SOLO("solo"),
    DUO("duo"),
    GROUP("group");

    private String artistType;

    private ArtistType(String artistType) {
        this.artistType = artistType;
    }

    public static ArtistType fromString(String type) {
        if (!type.isEmpty() && type != null) {
            for (ArtistType aType : ArtistType.values()) {
                if (type.equalsIgnoreCase(aType.name())) {
                    return aType;
                }
            }
        }
        throw new IllegalArgumentException("Invalid artist type: " + type);
    }

    public String getArtistType() {
        return artistType;
    }
}
