/*###############################################################################################################################*/

/*Preencher a tabela Continent*/
/*Insert Manual*/
insert into RegionsSchema.Continent values ('Asia')
insert into RegionsSchema.Continent values ('Africa')
insert into RegionsSchema.Continent values ('Europe')
insert into RegionsSchema.Continent values ('North America')
insert into RegionsSchema.Continent values ('South America')
insert into RegionsSchema.Continent values ('Oceania')
insert into RegionsSchema.Continent values ('Antarctica')

/*Por migração*/
insert into RegionsSchema.Continent
select 
	[Continent]
from WWI_DS.dbo.[City]
group by [Continent]


/*###############################################################################################################################*/

/*Preencher a tabela Country*/
/*Insert Manual*/
insert into RegionsSchema.country values ('Afghanistan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('�land Islands', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Albania', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Algeria', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('American Samoa', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Andorra', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Angola', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Anguilla', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Antarctica', (select idContinent from RegionsSchema.Continent where name like '%Antarctica%'))
insert into RegionsSchema.Country values ('Antigua and Barbuda', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Argentina', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Armenia', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Aruba', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Australia', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Austria', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Azerbaijan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Bahamas', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Bahrain', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Bangladesh', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Barbados', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Belarus', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Belgium', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Belize', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Benin', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Bermuda', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Bhutan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Bolivia (Plurinational State of)', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Bonaire, Sint Eustatius and Saba', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Bosnia and Herzegovina', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Botswana', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Bouvet Island', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Brazil', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('British Indian Ocean Territory', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('British Virgin Islands', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Brunei Darussalam', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Bulgaria', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Burkina Faso', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Burundi', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Cabo Verde', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Cambodia', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Cameroon', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Canada', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Cayman Islands', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Central African Republic', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Chad', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Chile', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('China', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('China, Hong Kong Special Administrative Region', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('China, Macao Special Administrative Region', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Christmas Island', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Cocos (Keeling) Islands', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Colombia', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Comoros', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Congo', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Cook Islands', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Costa Rica', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('C�te d�Ivoire', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Croatia', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Cuba', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Cura�ao', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Cyprus', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Czechia', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Democratic People''s Republic of Korea', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Democratic Republic of the Congo', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Denmark', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Djibouti', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Dominica', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Dominican Republic', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Ecuador', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Egypt', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('El Salvador', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Equatorial Guinea', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Eritrea', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Estonia', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Eswatini', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Ethiopia', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Falkland Islands (Malvinas)', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Faroe Islands', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Fiji', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Finland', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('France', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('French Guiana', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('French Polynesia', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('French Southern Territories', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Gabon', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Gambia', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Georgia', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Germany', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Ghana', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Gibraltar', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Greece', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Greenland', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Grenada', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Guadeloupe', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Guam', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Guatemala', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Guernsey', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Guinea', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Guinea-Bissau', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Guyana', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Haiti', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Heard Island and McDonald Islands', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Holy See', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Honduras', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Hungary', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Iceland', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('India', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Indonesia', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Iran (Islamic Republic of)', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Iraq', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Ireland', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Isle of Man', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Israel', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Italy', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Jamaica', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Japan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Jersey', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Jordan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Kazakhstan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Kenya', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Kiribati', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Kuwait', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Kyrgyzstan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Lao People''s Democratic Republic', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Latvia', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Lebanon', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Lesotho', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Liberia', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Libya', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Liechtenstein', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Lithuania', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Luxembourg', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Madagascar', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Malawi', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Malaysia', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Maldives', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Mali', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Malta', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Marshall Islands', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Martinique', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Mauritania', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Mauritius', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Mayotte', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Mexico', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Micronesia (Federated States of)', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Monaco', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Mongolia', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Montenegro', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Montserrat', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Morocco', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Mozambique', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Myanmar', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Namibia', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Nauru', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Nepal', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Netherlands', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('New Caledonia', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('New Zealand', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Nicaragua', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Niger', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Nigeria', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Niue', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Norfolk Island', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('North Macedonia', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Northern Mariana Islands', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Norway', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Oman', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Pakistan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Palau', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Panama', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Papua New Guinea', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Paraguay', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Peru', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Philippines', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Pitcairn', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Poland', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Portugal', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Puerto Rico', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Qatar', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Republic of Korea', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Republic of Moldova', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('R�union', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Romania', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Russian Federation', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Rwanda', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Saint Barth�lemy', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Saint Helena', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Saint Kitts and Nevis', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Saint Lucia', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Saint Martin (French Part)', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Saint Pierre and Miquelon', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Saint Vincent and the Grenadines', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Samoa', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('San Marino', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Sao Tome and Principe', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Sark', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Saudi Arabia', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Senegal', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Serbia', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Seychelles', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Sierra Leone', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Singapore', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Sint Maarten (Dutch part)', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Slovakia', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Slovenia', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Solomon Islands', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Somalia', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('South Africa', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('South Georgia and the South Sandwich Islands', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('South Sudan', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Spain', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Sri Lanka', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('State of Palestine', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Sudan', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Suriname', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Svalbard and Jan Mayen Islands', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Sweden', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Switzerland', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('Syrian Arab Republic', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Tajikistan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Thailand', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Timor-Leste', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Togo', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Tokelau', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Tonga', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Trinidad and Tobago', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Tunisia', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Turkey', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Turkmenistan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Turks and Caicos Islands', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Tuvalu', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Uganda', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Ukraine', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('United Arab Emirates', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('United Kingdom of Great Britain and Northern Ireland', (select idContinent from RegionsSchema.Continent where name like '%Europe%'))
insert into RegionsSchema.Country values ('United Republic of Tanzania', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('United States Minor Outlying Islands', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('United States of America', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('United States', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('United States Virgin Islands', (select idContinent from RegionsSchema.Continent where name like '%North America%'))
insert into RegionsSchema.Country values ('Uruguay', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Uzbekistan', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Vanuatu', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Venezuela (Bolivarian Republic of)', (select idContinent from RegionsSchema.Continent where name like '%South America%'))
insert into RegionsSchema.Country values ('Viet Nam', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Wallis and Futuna Islands', (select idContinent from RegionsSchema.Continent where name like '%Oceania%'))
insert into RegionsSchema.Country values ('Western Sahara', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Yemen', (select idContinent from RegionsSchema.Continent where name like '%Asia%'))
insert into RegionsSchema.Country values ('Zambia', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))
insert into RegionsSchema.Country values ('Zimbabwe', (select idContinent from RegionsSchema.Continent where name like '%Africa%'))

/*Por migração*/
insert into RegionsSchema.Country
select 
	[Country],
	(select idContinent from RegionsSchema.Continent where name like '%' + [Continent] + '%' COLLATE Latin1_General_100_CI_AS +'')
from WWI_DS.dbo.[City]
group by [Country], [Continent]


/*###############################################################################################################################*/

/*!ATENÇÃO! - O caminho do ficheiro é definido a partir da localização dos ficheiros pretendidos ir buscar a informação*/
BULK INSERT RegionsSchema.State
FROM 'C:\Users\LENOVO\Desktop\ProjetoCBD_WWI_FernandoRamalho_GuilhermeBernardino\Wide World Importers\states.txt' --Localização do computador pessoal (idealmente seria buscado a partir de uma localização fixa de um servidor)
WITH 
  (
	FIRSTROW = 2,
    FIELDTERMINATOR = ';', 
    ROWTERMINATOR = '\n' 
  )

/*Renomeação de alguns registos*/
UPDATE RegionsSchema.State
set name = 'Massachusetts[E]'
WHERE idState = 'MA'

UPDATE RegionsSchema.State
set name = 'Puerto Rico (US Territory)'
WHERE idState = 'PR'

UPDATE RegionsSchema.State
set name = 'Virgin Islands (US Territory)'
WHERE idState = 'VI'

/*###############################################################################################################################*/

/*Preencher a tabela CountryState*/
insert into RegionsSchema.CountryState
select 
(select idState from RegionsSchema.State where name = c.[State Province] COLLATE Latin1_General_100_CI_AS ) as [States],
(select idCountry from RegionsSchema.Country where name = c.[Country] COLLATE Latin1_General_100_CI_AS ) as [Country]
  from [WWI_DS].[dbo].City as c 
  group by c.[Country], c.[State Province] 

/*###############################################################################################################################*/

/*Preencher a tabela Color*/
--insert into ProductsSchema.color (idColor, name) values (0, 'N/A')
--insert into ProductsSchema.color (idColor, name) values (1, 'Black')
--insert into ProductsSchema.color (idColor, name) values (2, 'Blue')
--insert into ProductsSchema.color (idColor, name) values (3, 'Gray')
--insert into ProductsSchema.color (idColor, name) values (4, 'Light Brown')
--insert into ProductsSchema.color (idColor, name) values (5, 'Red')
--insert into ProductsSchema.color (idColor, name) values (6, 'Steel Gray')
--insert into ProductsSchema.color (idColor, name) values (7, 'White')
--insert into ProductsSchema.color (idColor, name) values (8, 'Yellow')

insert into ProductsSchema.Color 
select [Color] from WWI_DS.dbo.[Stock Item]
group by [Color]


/*###############################################################################################################################*/

/*Preencher a tabela Discount*/
insert into SalesSchema.Discount values(0,'1753-01-01T00:00:00' ,'9999-12-31T23:59:59')
insert into SalesSchema.Discount values(25,'2022-04-23T06:00:00', '2022-04-30T20:00:00')
insert into SalesSchema.Discount values(50,'2022-02-11T06:00:00', '2022-02-25T20:00:00')

/*###############################################################################################################################*/

/*Preencher a tabela Package*/
--insert into ProductsSchema.package (idPackage, name) values (0, 'N/A')
--insert into ProductsSchema.package (idPackage, name) values (1, 'Bag')
--insert into ProductsSchema.package (idPackage, name) values (2, 'Carton')
--insert into ProductsSchema.package (idPackage, name) values (3, 'Each')
--insert into ProductsSchema.package (idPackage, name) values (4, 'Packet')
--insert into ProductsSchema.package (idPackage, name) values (5, 'Pair')

insert into ProductsSchema.Package 
select [Package] from WWI_DS.dbo.Sale
group by [Package]

/*###############################################################################################################################*/

/*Preencher a tabela Category*/
--insert into ProductsSchema.category values ('Novelty Shop')
--insert into ProductsSchema.category values ('Gas Station Shop')
--insert into ProductsSchema.category values ('Gift Shop')
--insert into ProductsSchema.category values ('24H Shop')
--insert into ProductsSchema.category values ('Kiosk')

insert into ProductsSchema.Category
select [Category] from WWI_DS.dbo.Customer
group by [Category]


/*###############################################################################################################################*/

/*Preencher a tabela City*/
insert into RegionsSchema.City
	select 
		[City Key]
		,city
		,case when st is null then 'VI' ELSE st end,[Sales Territory] as st1
		,[Latest Recorded Population]
	from (
	select 
		[City Key],
		city,
		case when (select idstate from RegionsSchema.State where [name] =''+ [State Province] COLLATE Latin1_General_100_CI_AS +'') is null then 
		( select idstate from RegionsSchema.State where [State Province] like '%'+ [name] COLLATE Latin1_General_100_CI_AS +'%' ) 
		else
		(select idstate from RegionsSchema.State where [name] =''+ [State Province] COLLATE Latin1_General_100_CI_AS +'') end as st
		, 
		[Sales Territory],
		[Latest Recorded Population]
	from wwi_ds.dbo.City  
	) as q

/*###############################################################################################################################*/

/*Preencher a tabela BuyingGroup*/
insert into UserSchema.BuyingGroup 
select [Buying Group] from wwi_ds.dbo.Customer
group by [Buying Group]

/*###############################################################################################################################*/

/*Preencher a tabela Person Type com os 2 tipos de pessoas identificadas*/
insert into UserSchema.persontype values ('Employee');
insert into UserSchema.persontype values ('Customer');

/*###############################################################################################################################*/

/*Preencher a tabela Person com os dados dos Employee*/
insert into UserSchema.Person 
select [Employee]
	   ,[Preferred Name]
	   ,(Select REPLACE(Employee,' ','.')+'@WWI.com')
	   ,null
	   ,Photo
	   ,1
from WWI_DS.dbo.Employee;

/*###############################################################################################################################*/

/*Preencher a tabela Person com os dados dos Contact Person do customer*/
insert into UserSchema.Person
select distinct [Primary Contact]
	  ,[Primary Contact]
	  ,(Select REPLACE([Primary Contact],' ','.')+'@WWI.com')
	  ,null
	  ,null
	  ,2
from WWI_DS.dbo.Customer

/*###############################################################################################################################*/

/*Preencher a tabela Employee*/
insert into UserSchema.Employee
select idPerson
	   ,null
	   ,getdate()
	   ,null
from UserSchema.Person
where personTypeId=1;

/*###############################################################################################################################*/

/*Atualizacoees necessarias para se conseguir unificar os dados do customer no passo seguinte*/
update WWI_DS.dbo.Customer set Category = 'Kiosk' where Category='Quiosk';
update WWI_DS.dbo.Customer set Category = 'Gift Shop' where Category='GiftShop';

/*###############################################################################################################################*/

/*Preencher a tabela Customer*/
insert into UserSchema.Customer
select 
		p.idPerson
       ,p.name
	   ,(select idbuyingGroup from UserSchema.BuyingGroup where name = dsC.[Buying Group] COLLATE Latin1_General_100_CI_AS) as bg
	   ,(select idCategory from ProductsSchema.Category where [name] = dsC.Category COLLATE Latin1_General_100_CI_AS) as ct
	   ,[Postal Code]
	   ,dsc.[WWI Customer ID]
	   from WWI_DS.dbo.Customer dsC
inner join UserSchema.Person p on p.[name] = dsc.[Primary Contact] COLLATE Latin1_General_100_CI_AS
and p.personTypeId=2;

/*###############################################################################################################################*/

/*Preencher a tabela Produto*/
insert into ProductsSchema.Product
select 
	[Stock Item]
	,(select idcolor from ProductsSchema.Color where name = si.Color COLLATE Latin1_General_100_CI_AS)
	,(select idDiscount from SalesSchema.Discount where idDiscount = 1)
	,[Unit Price]
	,[Tax Rate]
	,Brand
	,Size
	,Barcode
	,[Recommended Retail Price]
	,[Typical Weight Per Unit]
	,[Lead Time Days]
	,[Quantity Per Outer]
from wwi_ds.dbo.[Stock Item] si  
order by [Stock Item Key]

/*###############################################################################################################################*/
  /*Preencher a tabela Invoice*/
  insert into SalesSchema.Invoice
  select 
  [WWI Invoice ID],
  [Invoice Date Key]
  from 
     [WWI_DS].[dbo].[Sale]
	 group by [WWI Invoice ID], [Invoice Date Key]

/*###############################################################################################################################*/
/*Preencher a tabela sale*/
   insert into SalesSchema.Sale 
  select 
  [sale key],
  [delivery date key],
  (select idEmployee from UserSchema.Employee where idEmployee=[Salesperson Key]),
  (select idCustomer from UserSchema.Customer where idCustomer =[Customer Key]),
  (select idInvoice from SalesSchema.Invoice where idInvoice=[WWI Invoice ID]),
  (select idCity from RegionsSchema.City where idCity=[City Key]),
  Description
  from 
     [WWI_DS].[dbo].[Sale]
	   order by [Sale Key]

/*###############################################################################################################################*/

/*Preencher a tabela saleproduct*/
insert into SalesSchema.SaleProduct 
	select 
	(select idproduct from ProductsSchema.Product where idProduct=[Stock Item Key]),
	(select idSale from SalesSchema.Sale where idSale=[Sale Key]),
	Quantity,
	Description,
	[Total Excluding Tax],
	[Tax Amount],
	[profit], 
	[Total Including Tax],
	[Total Dry Items],
	[Total Chiller Items]
		from WWI_DS.dbo.Sale

/*###############################################################################################################################*/

/*Preencher a tabela Stock*/

insert into ProductsSchema.Stock
select
    idProduct
    ,0
    ,1
    ,2
    ,0
from ProductsSchema.Product;

/*###############################################################################################################################*/


