package com.example.gl.model;

import com.example.gl.response.ResponseForSingleGameModel;

import java.io.Serializable;
import java.util.List;

public class GameSingleModel implements Serializable
{

    private Integer id;
    private Number rating;
    private String slug;
    private String name;
    private String description;
    private String released;
    private String background_image;
    private String websiteURL;
    private Integer playtime;
    private List<ResponseForSingleGameModel> platforms;
    private Integer game_series_count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Number getRating() {
        return rating;
    }

    public void setRating(Number rating) {
        this.rating = rating;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image)
    {

        this.background_image = background_image;
        
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public List<ResponseForSingleGameModel> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<ResponseForSingleGameModel> platforms) {
        this.platforms.addAll(platforms);
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public Integer getGame_series_count() {
        return game_series_count;
    }

    public void setGame_series_count(Integer game_series_count) {
        this.game_series_count = game_series_count;
    }
}
