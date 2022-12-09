package com.hybris.demogroup3.core.job;

import com.hybris.demogroup3.core.jalo.KasurProductDemo;
import com.hybris.demogroup3.core.model.KasurProductDemoModel;
import com.hybris.demogroup3.core.model.KasurVariantProductDemoModel;
import com.hybris.demogroup3.core.model.ShofaVariantProductDemoModel;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.commerceservices.setup.SetupSolrIndexerService;
import de.hybris.platform.commerceservices.setup.SetupSyncJobService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CronjobDemo extends AbstractJobPerformable {

    @Resource
    private SetupSyncJobService setupSyncJobService;

    @Resource
    private SetupSolrIndexerService setupSolrIndexerService;

    private static final Logger LOG = Logger.getLogger(AbstractJobPerformable.class);
    private static final String PRODUCT_CATALOG = "demoGroup3ProductCatalog";
    private static final String CONTENT_CATALOG = "demoGroup3ContentCatalog";

    private static final String INDEXED_TYPE = "demoGroup3Index";

    @Resource
    private FlexibleSearchService flexibleSearchService;


    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            LOG.info(dtf.format(now));

            final FlexibleSearchQuery query = new FlexibleSearchQuery("Select {pk} from {ShofaVariantProductDemo}");
            final SearchResult<ShofaVariantProductDemoModel> searchResult = flexibleSearchService.search(query);
            List<ShofaVariantProductDemoModel> shofaVariantProductDemoModelList = searchResult.getResult();
            if (CollectionUtils.isNotEmpty(shofaVariantProductDemoModelList)) {
                for (ShofaVariantProductDemoModel shofaVariantProductDemoModel : shofaVariantProductDemoModelList) {
                    LOG.info("Approval Status Product Shofa Now Is : " + shofaVariantProductDemoModel.getApprovalStatus());
                    shofaVariantProductDemoModel.setApprovalStatus(ArticleApprovalStatus.APPROVED);
                    modelService.save(shofaVariantProductDemoModel);
                    LOG.info("New Approval Status Product Shofa  Is : " + shofaVariantProductDemoModel.getApprovalStatus());
                }
            }
            final FlexibleSearchQuery query2 = new FlexibleSearchQuery("Select {pk} from {KasurVariantProductDemo}");
            final SearchResult<KasurVariantProductDemoModel> searchResult2 = flexibleSearchService.search(query2);
            List<KasurVariantProductDemoModel> kasurVariantProductDemoModelList = searchResult2.getResult();
            if (CollectionUtils.isNotEmpty(kasurVariantProductDemoModelList)) {
                for (KasurVariantProductDemoModel kasurVariantProductDemoModel : kasurVariantProductDemoModelList) {
                    LOG.info("Approval Status Product Kasur Now Is : " + kasurVariantProductDemoModel.getApprovalStatus());
                    kasurVariantProductDemoModel.setApprovalStatus(ArticleApprovalStatus.APPROVED);
                    modelService.save(kasurVariantProductDemoModel);
                    LOG.info("New Approval Status Product Kasur Is : " + kasurVariantProductDemoModel.getApprovalStatus());
                }
            }

            setupSyncJobService.createProductCatalogSyncJob(PRODUCT_CATALOG);
            setupSyncJobService.createContentCatalogSyncJob(CONTENT_CATALOG);
            setupSyncJobService.executeCatalogSyncJob(PRODUCT_CATALOG);
            setupSyncJobService.executeCatalogSyncJob(CONTENT_CATALOG);
            LOG.info("Product is Sync");
            setupSolrIndexerService.createSolrIndexerCronJobs(INDEXED_TYPE);
            setupSolrIndexerService.executeSolrIndexerCronJob(INDEXED_TYPE,true);
            LOG.info("Product is Indexing");
        }catch (Error e){
            LOG.error(e);
        }
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
