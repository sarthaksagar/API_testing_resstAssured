package com.api.tests.asset;

import com.api.models.asset.AssetTypeDTO;
import com.api.tests.base.BaseTest;
import com.api.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Test Suite for Asset Management (/api/asset-types, /api/asset-inventories).
 */
public class AssetTests extends BaseTest {

    private Long createdAssetTypeId;

    @Test(priority = 1, description = "TC_AST_POST_001: Create asset type with valid data")
    public void testCreateAssetTypeSuccess() {
        String typeName = TestDataGenerator.getUniqueAssetTypeName();
        AssetTypeDTO dto = new AssetTypeDTO(typeName, defaultCompanyId, "ACTIVE");

        Response response = assetClient.createAssetType(dto);
        Assert.assertTrue(response.getStatusCode() == 201 || response.getStatusCode() == 200);

        createdAssetTypeId = extractId(response);
        Assert.assertEquals(response.jsonPath().getString("assetTypeName"), typeName);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateAssetTypeSuccess", description = "TC_AST_GET_001: Get asset type by valid ID")
    public void testGetAssetTypeByIdSuccess() {
        Response response = assetClient.getAssetTypeById(createdAssetTypeId);
        assertStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getLong("id"), createdAssetTypeId.longValue());
    }

    @Test(priority = 3, description = "TC_AST_GETALL_001: Get all asset types")
    public void testGetAllAssetTypes() {
        Response response = assetClient.getAllAssetTypes();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 4, description = "TC_AST_COUNT_001: Get asset type count")
    public void testGetAssetTypeCount() {
        Response response = assetClient.getAssetTypeCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"));
    }

    @Test(priority = 5, description = "TC_AI_GETALL_001: Get all asset inventories")
    public void testGetAllAssetInventories() {
        Response response = assetClient.getAllAssetInventories();
        assertStatusCode(response, 200);
        response.then().body("size()", greaterThanOrEqualTo(0));
    }

    @Test(priority = 6, description = "TC_AI_COUNT_001: Get asset inventory count")
    public void testGetAssetInventoryCount() {
        Response response = assetClient.getAssetInventoryCount();
        assertStatusCode(response, 200);
        Assert.assertTrue(response.getBody().asString().matches("\\d+"));
    }

    @Test(priority = 7, dependsOnMethods = "testCreateAssetTypeSuccess", description = "TC_AST_DELETE_001: Delete asset type by valid ID")
    public void testDeleteAssetTypeSuccess() {
        Response response = assetClient.deleteAssetType(createdAssetTypeId);
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);

        Response verify = assetClient.getAssetTypeById(createdAssetTypeId);
        Assert.assertEquals(verify.getStatusCode(), 404);
    }
}
