drop table if exists tbl_article;

create table tbl_article
(
    artifact_id             bigint auto_increment primary key,
    artifact_insertion_date timestamp,
    artifact_author         varchar(2000),
    artifact_title          varchar(2000),
    artifact_desc           varchar(100000),
    artifact_url            varchar(2000),
    artifact_url_img        varchar(2000),
    artifact_published_at   varchar(2000),
    source_name             varchar(2000),
    source_desc             varchar(100000),
    source_url              varchar(2000),
    source_category         varchar(2000),
    source_lang             varchar(2000),
    source_country          varchar(2000)
);

insert into tbl_article (artifact_id, artifact_insertion_date, artifact_author, artifact_title, artifact_desc,
                         artifact_url, artifact_url_img, artifact_published_at, source_name, source_desc, source_url,
                         source_category, source_lang, source_country)
values (null, current_timestamp, 'artifact_author', 'artifact_title', 'artifact_desc', 'artifact_url',
        'artifact_url_img', 'artifact_published_at', 'source_name', 'source_desc', 'source_url', 'source_category',
        'source_lang', 'source_country');
