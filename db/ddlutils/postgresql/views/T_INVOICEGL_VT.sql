﻿DROP VIEW t_invoicegl_vt;

CREATE OR REPLACE VIEW t_invoicegl_vt AS 
SELECT i.ad_client_id,
    i.ad_org_id,
    i.isactive,
    i.created,
    i.createdby,
    i.updated,
    i.updatedby,
    i.c_invoice_id,
    i.issotrx,
    i.documentno,
    i.docstatus,
    i.c_doctype_id,
    i.c_order_id,
    i.description,
    i.salesrep_id,
    i.dateinvoiced,
    i.dateacct,
    i.c_paymentterm_id,
    i.c_bpartner_id,
    i.c_bpartner_location_id,
    i.ad_user_id,
    i.isselfservice,
    i.c_currency_id,
    i.c_conversiontype_id,
    i.grandtotal,
    i.istaxincluded,
    i.c_campaign_id,
    i.c_project_id,
    i.c_activity_id,
    i.ad_orgtrx_id,
    i.user1_id,
    i.user2_id,
    fa.c_locfrom_id,
    fa.c_locto_id,
    fa.c_salesregion_id,
    fa.fact_acct_id,
    fa.c_acctschema_id,
    fa.account_id,
    fa.c_period_id,
    fa.gl_category_id,
    fa.gl_budget_id,
    fa.c_tax_id,
    fa.m_locator_id,
    fa.postingtype,
    fa.amtsourcedr,
    fa.amtsourcecr,
    fa.amtacctdr,
    fa.amtacctcr,
    fa.c_uom_id,
    fa.qty,
    gl.ad_pinstance_id,
    gl.apar,
    gl.openamt,
    gl.percent,
    gl.amtrevaldr,
    gl.amtrevalcr,
    gl.datereval,
    gl.c_conversiontypereval_id,
    gl.amtsourcebalance,
    gl.amtacctbalance,
    gl.c_doctypereval_id,
    gl.amtrevaldrdiff,
    gl.amtrevalcrdiff,
    gl.isallcurrencies,
    fa.amtacctdr * gl.percent / 100                  AS amtacctopendr,
    fa.amtacctcr * gl.percent / 100                  AS amtacctopencr,
    (fa.amtacctdr - fa.amtacctcr) * gl.percent / 100 AS amtacctopenbalance,
    gl.ad_org_id                                     AS t_invoicegl_ad_org_id,
    gl.c_invoice_id                                  AS t_invoicegl_c_invoice_id,
    gl.created                                       AS t_invoicegl_created,
    gl.createdby                                     AS t_invoicegl_createdby,
    gl.grandtotal                                    AS t_invoicegl_grandtotal,
    gl.isactive                                      AS t_invoicegl_isactive,
    gl.updated                                       AS t_invoicegl_updated,
    gl.updatedby                                     AS t_invoicegl_updatedby,
    i.c_charge_id                                    AS c_invoice_c_charge_id,
    i.c_doctypetarget_id,
    i.c_dunninglevel_id,
    i.chargeamt,
    i.c_payment_id,
    i.dateordered,
    i.dateprinted,
    i.docaction,
    i.dunninggrace,
    i.generateto,
    i.invoicecollectiontype,
    i.isapproved,
    i.isdiscountprinted,
    i.isindispute,
    i.ispaid,
    i.ispayschedulevalid,
    i.isprinted,
    i.istransferred,
    i.m_pricelist_id,
    i.m_rma_id,
    i.paymentrule,
    i.poreference,
    i.posted,
    i.processedon,
    i.processing,
    i.ref_invoice_id,
    i.reversal_id,
    i.sendemail,
    i.totallines,
    fa.a_asset_id,
    fa.ad_org_id                                     AS fact_acct_ad_org_id,
    fa.ad_orgtrx_id                                  AS fact_acct_ad_orgtrx_id,
    fa.ad_table_id                                   AS fact_acct_ad_table_id,
    fa.c_activity_id                                 AS fact_acct_c_activity_id,
    fa.c_bpartner_id                                 AS fact_acct_c_bpartner_id,
    fa.c_campaign_id                                 AS fact_acct_c_campaign_id,
    fa.c_currency_id                                 AS fact_acct_c_currency_id,
    fa.c_subacct_id,
    fa.dateacct                                      AS fact_acct_dateacct,
    fa.datetrx,
    fa.description                                   AS fact_acct_description,
    fa.isactive                                      AS fact_acct_isactive,
    fa.line_id,
    fa.m_product_id                                  AS fact_acct_m_product_id,
    fa.record_id,
    fa.updated                                       AS fact_acct_updated,
    fa.updatedby                                     AS fact_acct_updatedby,
    fa.user1_id                                      AS fact_acct_user1_id,
    fa.user2_id                                      AS fact_acct_user2_id,
    fa.userelement1_id,
    fa.userelement2_id 
FROM t_invoicegl gl 
        JOIN c_invoice i 
        ON gl.c_invoice_id = i.c_invoice_id 
        JOIN fact_acct fa 
        ON gl.fact_acct_id = fa.fact_acct_id
;
