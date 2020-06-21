/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: 1C3OaPprBQkrIDVVYaWGPwQ/kVmKHYgr
 */
package com.igomall.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import com.igomall.service.CacheService;
import com.igomall.service.ConfigService;

/**
 * Service - 缓存
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Service
public class CacheServiceImpl implements CacheService {

	@Resource
	private CacheManager cacheManager;
	@Resource
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	@Resource
	private ConfigService configService;

	@Override
	public String getDiskStorePath() {
		return cacheManager.getConfiguration().getDiskStoreConfiguration().getPath();
	}

	@Override
	public int getCacheSize() {
		int cacheSize = 0;
		String[] cacheNames = cacheManager.getCacheNames();
		if (cacheNames != null) {
			for (String cacheName : cacheNames) {
				Ehcache cache = cacheManager.getEhcache(cacheName);
				if (cache != null) {
					cacheSize += cache.getSize();
				}
			}
		}
		return cacheSize;
	}

	@Override
	@CacheEvict(value = { "setting", "templateConfig", "pluginConfig", "messageConfig", "indexPage", "storeIndexPage", "articleListPage", "articleDetailPage", "productListPage", "productDetailPage", "brandListPage", "brandDetailPage", "productCategoryPage", "promotionDetailPage", "friendLinkPage",
			"consultationDetailPage", "reviewDetailPage", "areaPage", "baseJsPage", "sitemapPage", "seo", "adPosition", "memberAttribute", "businessAttribute", "navigation", "friendLink", "instantMessage", "brand", "attribute", "article", "articleCategory", "articleTag", "product",
			"productCategory", "productTag", "storeProductCategory", "storeProductTag", "storeAdImage", "productFavorite", "storeFavorite", "consultation", "review", "promotion", "transitSteps", "authorization" }, allEntries = true)
	public void clear() {
		freeMarkerConfigurer.getConfiguration().clearTemplateCache();
		reloadableResourceBundleMessageSource.clearCache();
		configService.init();
	}

}