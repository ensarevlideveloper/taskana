--                                ID,                                         KEY,               PARENT_ID,                                  CATEGORY,     TYPE,       DOMAIN,     VALID_IN_DOMAIN, CREATED,            NAME,                             DESCRIPTION,                                           PRIORITY, SERVICE_LEVEL, APPLICATION_ENTRY_POINT, CUSTOM_1 - 8
-- ROOT CLASSIFICATIONS
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000001', 'L10000',          '',                                         'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'OLD-Leistungsfall',               'OLD-Leistungsfall',                                   999,      'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000002', 'L10303',          '',                                         'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'Beratungsprotokoll',              'Beratungsprotokoll',                                  1,        'P1D',         '',                      'VNR,RVNR,KOLVNR, ANR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000003', 'L1050',           '',                                         'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'Widerruf',                        'Widerruf',                                            1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000004', 'L11010',          '',                                         'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'Dynamikänderung',                 'Dynamikänderung',                                     1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000005', 'L110102',         'CLI:000000000000000000000000000000000004', 'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'Dynamik-Ablehnung',               'Dynamik-Ablehnung',                                   5,        'P5D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000006', 'L110105',         'CLI:000000000000000000000000000000000004', 'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'Dynamik-Ausschluss',              'Dynamik-Ausschluss',                                  5,        'P5D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000007', 'L110107',         'CLI:000000000000000000000000000000000004', 'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'Dynamik-Einschluss/Änd.',         'Dynamik-Einschluss/Änd.',                             5,        'P5D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000008', 'L12010',          '',                                         'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'Gewährung-Policendarlehen',       'Gewährung-Policendarlehen',                           1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000009', 'L140101',         '',                                         'EXTERN',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'Zustimmungserklärung',            'Zustimmungserklärung',                                2,        'P2D',         '',                      'VNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000010', 'T2100',           '',                                         'MANUAL',     'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'T-Vertragstermin VERA',           'T-Vertragstermin VERA',                               2,        'P2D',         '',                      'VNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000011', 'T6310',           '',                                         'AUTOMATIC',  'TASK',     '',         FALSE,           CURRENT_TIMESTAMP, 'T-GUK Honorarrechnung erstellen', 'Generali Unterstützungskasse Honorar wird fällig',    2,        'P2D',         '',                      'VNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:000000000000000000000000000000000013', 'DOCTYPE_DEFAULT', '',                                         'EXTERN',     'DOCUMENT', '',         FALSE,           CURRENT_TIMESTAMP, 'EP allgemein',                    'EP allgemein',                                        99,       'P2000D',      '',                      'VNR', '', '', '', '', '', '', '');

-- DOMAIN_A CLASSIFICATIONS
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000002', 'L10303',          '',                                         'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Beratungsprotokoll',              'Beratungsprotokoll',                                  1,        'P1D',         '',                      'VNR,RVNR,KOLVNR, ANR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000003', 'L1050',           '',                                         'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Widerruf',                        'Widerruf',                                            1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000004', 'L11010',          '',                                         'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Dynamikänderung',                 'Dynamikänderung',                                     1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000005', 'L110102',         'CLI:100000000000000000000000000000000004', 'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Dynamik-Ablehnung',               'Dynamik-Ablehnung',                                   5,        'P5D',         '',                      'VNR,RVNR,KOLVNR', 'TEXT_1', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000006', 'L110105',         'CLI:100000000000000000000000000000000004', 'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Dynamik-Ausschluss',              'Dynamik-Ausschluss',                                  5,        'P5D',         '',                      'VNR,RVNR,KOLVNR', 'TEXT_2', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000007', 'L110107',         'CLI:100000000000000000000000000000000004', 'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Dynamik-Einschluss/Änd.',         'Dynamik-Einschluss/Änd.',                             5,        'P5D',         '',                      'VNR,RVNR,KOLVNR', 'TEXT_1', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000008', 'L12010',          '',                                         'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Gewährung-Policendarlehen',       'Gewährung-Policendarlehen',                           1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000009', 'L140101',         '',                                         'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Zustimmungserklärung',            'Zustimmungserklärung',                                2,        'P2D',         '',                      'VNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000010', 'T2100',           '',                                         'MANUAL',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'T-Vertragstermin VERA',           'T-Vertragstermin VERA',                               2,        'P2D',         '',                      'VNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000011', 'T6310',           '',                                         'AUTOMATIC',  'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'T-GUK Honorarrechnung erstellen', 'Generali Unterstützungskasse Honorar wird fällig',    2,        'P2D',         '',                      'VNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000013', 'DOCTYPE_DEFAULT', '',                                         'EXTERN',     'DOCUMENT', 'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'EP allgemein',                    'EP allgemein',                                        99,       'P2000D',      '',                      'VNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000014', 'L10000',          '',                                         'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'BUZ-Leistungsfall',               'BUZ-Leistungsfall',                                   1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', 'VNR', 'VNR', 'VNR', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000016', 'T2000',           '',                                         'MANUAL',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'T-Vertragstermin',                'T-Vertragstermin',                                    1,        'P1D',         '',                      'VNR,KOLVNR,RVNR', 'CUSTOM_2', 'Custom_3', 'custom_4', '', '', '', '');

-- DOMAIN_B CLASSIFICATIONS
INSERT INTO CLASSIFICATION VALUES('CLI:100000000000000000000000000000000015', 'T2100',           '',                                         'MANUAL',     'TASK',     'DOMAIN_B', TRUE,            CURRENT_TIMESTAMP, 'T-Vertragstermin VERA',           'T-Vertragstermin VERA',                               22,       'P2D',         '',                      'VNR', '', '', '', '', '', '', '');

-- WITH PARENT CLASSIFICATIONS (MIXED DOMAIN) ---
-- DOMAIN_A
INSERT INTO CLASSIFICATION VALUES('CLI:200000000000000000000000000000000001', 'A12',             'CLI:100000000000000000000000000000000014', 'EXTERN',     'TASK',     'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'OLD-Leistungsfall',               'OLD-Leistungsfall',                                   1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:200000000000000000000000000000000002', 'A13',             'CLI:100000000000000000000000000000000011', 'AUTOMATIC',  'EXTERN',   'DOMAIN_A', TRUE,            CURRENT_TIMESTAMP, 'Beratungsprotokoll',              'Beratungsprotokoll',                                  1,        'P1D',         '',                      'VNR,RVNR,KOLVNR, ANR', '', '', '', '', '', '', '');
-- DOMAIN_B
INSERT INTO CLASSIFICATION VALUES('CLI:200000000000000000000000000000000003', 'A12',             'CLI:100000000000000000000000000000000015', 'MANUAL',     'TASK',     'DOMAIN_B', TRUE,            CURRENT_TIMESTAMP, 'Widerruf',                        'Widerruf',                                            1,        'P1D',         '',                      'VNR,RVNR,KOLVNR', '', '', '', '', '', '', '');
INSERT INTO CLASSIFICATION VALUES('CLI:200000000000000000000000000000000004', 'T21001',          'CLI:100000000000000000000000000000000015', 'MANUAL',     'TASK',     'DOMAIN_B', TRUE,            CURRENT_TIMESTAMP, 'Beratungsprotokoll',              'Beratungsprotokoll',                                  1,        'P1D',         '',                      'VNR,RVNR,KOLVNR, ANR', '', '', '', '', '', '', '');
