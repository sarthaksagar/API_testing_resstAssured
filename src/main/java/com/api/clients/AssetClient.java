package com.api.clients;

import com.api.constants.Endpoints;
import com.api.models.asset.AssetTypeDTO;
import io.restassured.response.Response;

public class AssetClient extends BaseClient {

    public Response createAssetType(AssetTypeDTO assetType) {
        return request()
                .body(assetType)
                .when()
                .post(Endpoints.ASSET_TYPES);
    }

    public Response getAssetTypeById(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .get(Endpoints.ASSET_TYPE_BY_ID);
    }

    public Response getAllAssetTypes() {
        return request()
                .when()
                .get(Endpoints.ASSET_TYPES);
    }

    public Response deleteAssetType(Long id) {
        return request()
                .pathParam("id", id)
                .when()
                .delete(Endpoints.ASSET_TYPE_BY_ID);
    }

    public Response getAssetTypeCount() {
        return request()
                .when()
                .get(Endpoints.ASSET_TYPE_COUNT);
    }

    public Response getAllAssetInventories() {
        return request()
                .when()
                .get(Endpoints.ASSET_INVENTORIES);
    }

    public Response getAssetInventoryCount() {
        return request()
                .when()
                .get(Endpoints.ASSET_INVENTORY_COUNT);
    }
}
