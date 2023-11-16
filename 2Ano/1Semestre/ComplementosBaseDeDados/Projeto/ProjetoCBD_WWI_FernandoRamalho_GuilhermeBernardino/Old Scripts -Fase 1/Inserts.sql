/*###############################################################################################################################*/

/*Preencher a tabela Continent*/
insert into Continent values ('Asia')
insert into Continent values ('Africa')
insert into Continent values ('Europe')
insert into Continent values ('North America')
insert into Continent values ('South America')
insert into Continent values ('Oceania')
insert into Continent values ('Antarctica')


/*###############################################################################################################################*/

/*Preencher a tabela Country*/
insert into country values ('Afghanistan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Åland Islands', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Albania', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Algeria', (select idContinent from continent where name like '%Africa%'))
insert into country values ('American Samoa', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Andorra', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Angola', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Anguilla', (select idContinent from continent where name like '%North America%'))
insert into country values ('Antarctica', (select idContinent from continent where name like '%Antarctica%'))
insert into country values ('Antigua and Barbuda', (select idContinent from continent where name like '%North America%'))
insert into country values ('Argentina', (select idContinent from continent where name like '%South America%'))
insert into country values ('Armenia', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Aruba', (select idContinent from continent where name like '%North America%'))
insert into country values ('Australia', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Austria', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Azerbaijan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Bahamas', (select idContinent from continent where name like '%North America%'))
insert into country values ('Bahrain', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Bangladesh', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Barbados', (select idContinent from continent where name like '%North America%'))
insert into country values ('Belarus', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Belgium', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Belize', (select idContinent from continent where name like '%North America%'))
insert into country values ('Benin', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Bermuda', (select idContinent from continent where name like '%North America%'))
insert into country values ('Bhutan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Bolivia (Plurinational State of)', (select idContinent from continent where name like '%South America%'))
insert into country values ('Bonaire, Sint Eustatius and Saba', (select idContinent from continent where name like '%North America%'))
insert into country values ('Bosnia and Herzegovina', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Botswana', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Bouvet Island', (select idContinent from continent where name like '%South America%'))
insert into country values ('Brazil', (select idContinent from continent where name like '%South America%'))
insert into country values ('British Indian Ocean Territory', (select idContinent from continent where name like '%Africa%'))
insert into country values ('British Virgin Islands', (select idContinent from continent where name like '%North America%'))
insert into country values ('Brunei Darussalam', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Bulgaria', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Burkina Faso', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Burundi', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Cabo Verde', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Cambodia', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Cameroon', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Canada', (select idContinent from continent where name like '%North America%'))
insert into country values ('Cayman Islands', (select idContinent from continent where name like '%North America%'))
insert into country values ('Central African Republic', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Chad', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Chile', (select idContinent from continent where name like '%South America%'))
insert into country values ('China', (select idContinent from continent where name like '%Asia%'))
insert into country values ('China, Hong Kong Special Administrative Region', (select idContinent from continent where name like '%Asia%'))
insert into country values ('China, Macao Special Administrative Region', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Christmas Island', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Cocos (Keeling) Islands', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Colombia', (select idContinent from continent where name like '%South America%'))
insert into country values ('Comoros', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Congo', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Cook Islands', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Costa Rica', (select idContinent from continent where name like '%North America%'))
insert into country values ('Côte d’Ivoire', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Croatia', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Cuba', (select idContinent from continent where name like '%North America%'))
insert into country values ('Curaçao', (select idContinent from continent where name like '%North America%'))
insert into country values ('Cyprus', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Czechia', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Democratic People''s Republic of Korea', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Democratic Republic of the Congo', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Denmark', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Djibouti', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Dominica', (select idContinent from continent where name like '%North America%'))
insert into country values ('Dominican Republic', (select idContinent from continent where name like '%North America%'))
insert into country values ('Ecuador', (select idContinent from continent where name like '%South America%'))
insert into country values ('Egypt', (select idContinent from continent where name like '%Africa%'))
insert into country values ('El Salvador', (select idContinent from continent where name like '%North America%'))
insert into country values ('Equatorial Guinea', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Eritrea', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Estonia', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Eswatini', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Ethiopia', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Falkland Islands (Malvinas)', (select idContinent from continent where name like '%South America%'))
insert into country values ('Faroe Islands', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Fiji', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Finland', (select idContinent from continent where name like '%Europe%'))
insert into country values ('France', (select idContinent from continent where name like '%Europe%'))
insert into country values ('French Guiana', (select idContinent from continent where name like '%South America%'))
insert into country values ('French Polynesia', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('French Southern Territories', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Gabon', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Gambia', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Georgia', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Germany', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Ghana', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Gibraltar', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Greece', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Greenland', (select idContinent from continent where name like '%North America%'))
insert into country values ('Grenada', (select idContinent from continent where name like '%North America%'))
insert into country values ('Guadeloupe', (select idContinent from continent where name like '%North America%'))
insert into country values ('Guam', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Guatemala', (select idContinent from continent where name like '%North America%'))
insert into country values ('Guernsey', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Guinea', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Guinea-Bissau', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Guyana', (select idContinent from continent where name like '%South America%'))
insert into country values ('Haiti', (select idContinent from continent where name like '%North America%'))
insert into country values ('Heard Island and McDonald Islands', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Holy See', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Honduras', (select idContinent from continent where name like '%North America%'))
insert into country values ('Hungary', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Iceland', (select idContinent from continent where name like '%Europe%'))
insert into country values ('India', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Indonesia', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Iran (Islamic Republic of)', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Iraq', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Ireland', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Isle of Man', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Israel', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Italy', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Jamaica', (select idContinent from continent where name like '%North America%'))
insert into country values ('Japan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Jersey', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Jordan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Kazakhstan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Kenya', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Kiribati', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Kuwait', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Kyrgyzstan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Lao People''s Democratic Republic', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Latvia', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Lebanon', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Lesotho', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Liberia', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Libya', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Liechtenstein', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Lithuania', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Luxembourg', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Madagascar', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Malawi', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Malaysia', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Maldives', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Mali', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Malta', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Marshall Islands', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Martinique', (select idContinent from continent where name like '%North America%'))
insert into country values ('Mauritania', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Mauritius', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Mayotte', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Mexico', (select idContinent from continent where name like '%North America%'))
insert into country values ('Micronesia (Federated States of)', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Monaco', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Mongolia', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Montenegro', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Montserrat', (select idContinent from continent where name like '%North America%'))
insert into country values ('Morocco', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Mozambique', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Myanmar', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Namibia', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Nauru', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Nepal', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Netherlands', (select idContinent from continent where name like '%Europe%'))
insert into country values ('New Caledonia', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('New Zealand', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Nicaragua', (select idContinent from continent where name like '%North America%'))
insert into country values ('Niger', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Nigeria', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Niue', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Norfolk Island', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('North Macedonia', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Northern Mariana Islands', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Norway', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Oman', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Pakistan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Palau', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Panama', (select idContinent from continent where name like '%North America%'))
insert into country values ('Papua New Guinea', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Paraguay', (select idContinent from continent where name like '%South America%'))
insert into country values ('Peru', (select idContinent from continent where name like '%South America%'))
insert into country values ('Philippines', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Pitcairn', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Poland', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Portugal', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Puerto Rico', (select idContinent from continent where name like '%North America%'))
insert into country values ('Qatar', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Republic of Korea', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Republic of Moldova', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Réunion', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Romania', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Russian Federation', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Rwanda', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Saint Barthélemy', (select idContinent from continent where name like '%North America%'))
insert into country values ('Saint Helena', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Saint Kitts and Nevis', (select idContinent from continent where name like '%North America%'))
insert into country values ('Saint Lucia', (select idContinent from continent where name like '%North America%'))
insert into country values ('Saint Martin (French Part)', (select idContinent from continent where name like '%North America%'))
insert into country values ('Saint Pierre and Miquelon', (select idContinent from continent where name like '%North America%'))
insert into country values ('Saint Vincent and the Grenadines', (select idContinent from continent where name like '%North America%'))
insert into country values ('Samoa', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('San Marino', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Sao Tome and Principe', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Sark', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Saudi Arabia', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Senegal', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Serbia', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Seychelles', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Sierra Leone', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Singapore', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Sint Maarten (Dutch part)', (select idContinent from continent where name like '%North America%'))
insert into country values ('Slovakia', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Slovenia', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Solomon Islands', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Somalia', (select idContinent from continent where name like '%Africa%'))
insert into country values ('South Africa', (select idContinent from continent where name like '%Africa%'))
insert into country values ('South Georgia and the South Sandwich Islands', (select idContinent from continent where name like '%South America%'))
insert into country values ('South Sudan', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Spain', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Sri Lanka', (select idContinent from continent where name like '%Asia%'))
insert into country values ('State of Palestine', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Sudan', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Suriname', (select idContinent from continent where name like '%South America%'))
insert into country values ('Svalbard and Jan Mayen Islands', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Sweden', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Switzerland', (select idContinent from continent where name like '%Europe%'))
insert into country values ('Syrian Arab Republic', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Tajikistan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Thailand', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Timor-Leste', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Togo', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Tokelau', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Tonga', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Trinidad and Tobago', (select idContinent from continent where name like '%North America%'))
insert into country values ('Tunisia', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Turkey', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Turkmenistan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Turks and Caicos Islands', (select idContinent from continent where name like '%North America%'))
insert into country values ('Tuvalu', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Uganda', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Ukraine', (select idContinent from continent where name like '%Europe%'))
insert into country values ('United Arab Emirates', (select idContinent from continent where name like '%Asia%'))
insert into country values ('United Kingdom of Great Britain and Northern Ireland', (select idContinent from continent where name like '%Europe%'))
insert into country values ('United Republic of Tanzania', (select idContinent from continent where name like '%Africa%'))
insert into country values ('United States Minor Outlying Islands', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('United States of America', (select idContinent from continent where name like '%North America%'))
insert into country values ('United States Virgin Islands', (select idContinent from continent where name like '%North America%'))
insert into country values ('Uruguay', (select idContinent from continent where name like '%South America%'))
insert into country values ('Uzbekistan', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Vanuatu', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Venezuela (Bolivarian Republic of)', (select idContinent from continent where name like '%South America%'))
insert into country values ('Viet Nam', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Wallis and Futuna Islands', (select idContinent from continent where name like '%Oceania%'))
insert into country values ('Western Sahara', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Yemen', (select idContinent from continent where name like '%Asia%'))
insert into country values ('Zambia', (select idContinent from continent where name like '%Africa%'))
insert into country values ('Zimbabwe', (select idContinent from continent where name like '%Africa%'))

/*###############################################################################################################################*/

/*Preencher a tabela State*/
insert into state values ('AL', 'Alabama', (select idcountry from country where name = 'United States of America'))
insert into state values ('AK', 'Alaska', (select idcountry from country where name = 'United States of America'))
insert into state values ('AZ', 'Arizona', (select idcountry from country where name = 'United States of America'))
insert into state values ('AR', 'Arkansas', (select idcountry from country where name = 'United States of America'))
insert into state values ('CA', 'California', (select idcountry from country where name = 'United States of America'))
insert into state values ('CO', 'Colorado', (select idcountry from country where name = 'United States of America'))
insert into state values ('CT', 'Connecticut', (select idcountry from country where name = 'United States of America'))
insert into state values ('DE', 'Delaware', (select idcountry from country where name = 'United States of America'))
insert into state values ('FL', 'Florida', (select idcountry from country where name = 'United States of America'))
insert into state values ('GA', 'Georgia', (select idcountry from country where name = 'United States of America'))
insert into state values ('HI', 'Hawaii', (select idcountry from country where name = 'United States of America'))
insert into state values ('ID', 'Idaho', (select idcountry from country where name = 'United States of America'))
insert into state values ('IL', 'Illinois', (select idcountry from country where name = 'United States of America'))
insert into state values ('IN', 'Indiana', (select idcountry from country where name = 'United States of America'))
insert into state values ('IA', 'Iowa', (select idcountry from country where name = 'United States of America'))
insert into state values ('KS', 'Kansas', (select idcountry from country where name = 'United States of America'))
insert into state values ('KY', 'Kentucky', (select idcountry from country where name = 'United States of America'))
insert into state values ('LA', 'Louisiana', (select idcountry from country where name = 'United States of America'))
insert into state values ('ME', 'Maine', (select idcountry from country where name = 'United States of America'))
insert into state values ('MD', 'Maryland', (select idcountry from country where name = 'United States of America'))
insert into state values ('MA', 'Massachusetts', (select idcountry from country where name = 'United States of America'))
insert into state values ('MI', 'Michigan', (select idcountry from country where name = 'United States of America'))
insert into state values ('MN', 'Minnesota', (select idcountry from country where name = 'United States of America'))
insert into state values ('MS', 'Mississippi', (select idcountry from country where name = 'United States of America'))
insert into state values ('MO', 'Missouri', (select idcountry from country where name = 'United States of America'))
insert into state values ('MT', 'Montana', (select idcountry from country where name = 'United States of America'))
insert into state values ('NE', 'Nebraska', (select idcountry from country where name = 'United States of America'))
insert into state values ('NV', 'Nevada', (select idcountry from country where name = 'United States of America'))
insert into state values ('NH', 'New Hampshire', (select idcountry from country where name = 'United States of America'))
insert into state values ('NJ', 'New Jersey', (select idcountry from country where name = 'United States of America'))
insert into state values ('NM', 'New Mexico', (select idcountry from country where name = 'United States of America'))
insert into state values ('NY', 'New York', (select idcountry from country where name = 'United States of America'))
insert into state values ('NC', 'North Carolina', (select idcountry from country where name = 'United States of America'))
insert into state values ('ND', 'North Dakota', (select idcountry from country where name = 'United States of America'))
insert into state values ('OH', 'Ohio', (select idcountry from country where name = 'United States of America'))
insert into state values ('OK', 'Oklahoma', (select idcountry from country where name = 'United States of America'))
insert into state values ('OR', 'Oregon', (select idcountry from country where name = 'United States of America'))
insert into state values ('PA', 'Pennsylvania', (select idcountry from country where name = 'United States of America'))
insert into state values ('RI', 'Rhode Island', (select idcountry from country where name = 'United States of America'))
insert into state values ('SC', 'South Carolina', (select idcountry from country where name = 'United States of America'))
insert into state values ('SD', 'South Dakota', (select idcountry from country where name = 'United States of America'))
insert into state values ('TN', 'Tennessee', (select idcountry from country where name = 'United States of America'))
insert into state values ('TX', 'Texas', (select idcountry from country where name = 'United States of America'))
insert into state values ('UT', 'Utah', (select idcountry from country where name = 'United States of America'))
insert into state values ('VT', 'Vermont', (select idcountry from country where name = 'United States of America'))
insert into state values ('VA', 'Virginia', (select idcountry from country where name = 'United States of America'))
insert into state values ('WA', 'Washington', (select idcountry from country where name = 'United States of America'))
insert into state values ('WV', 'West Virginia', (select idcountry from country where name = 'United States of America'))
insert into state values ('WI', 'Wisconsin', (select idcountry from country where name = 'United States of America'))
insert into state values ('WY', 'Wyoming', (select idcountry from country where name = 'United States of America'))
insert into state values ('DC', 'District of Columbia', (select idcountry from country where name = 'United States of America'))
insert into state values ('AS', 'American Samoa', (select idcountry from country where name = 'United States of America'))
insert into state values ('GU', 'Guam', (select idcountry from country where name = 'United States of America'))
insert into state values ('MP', 'Northern Mariana Islands', (select idcountry from country where name = 'United States of America'))
insert into state values ('PR', 'Puerto Rico', (select idcountry from country where name = 'United States of America'))
insert into state values ('UM', 'United States Minor Outlying Islands', (select idcountry from country where name = 'United States of America'))
insert into state values ('VI', 'Virgin Islands, U.S.', (select idcountry from country where name = 'United States of America'))

/*###############################################################################################################################*/

/*Preencher a tabela Color*/
insert into color (idColor, name) values (0, 'N/A')
insert into color (idColor, name) values (1, 'Black')
insert into color (idColor, name) values (2, 'Blue')
insert into color (idColor, name) values (3, 'Gray')
insert into color (idColor, name) values (4, 'Light Brown')
insert into color (idColor, name) values (5, 'Red')
insert into color (idColor, name) values (6, 'Steel Gray')
insert into color (idColor, name) values (7, 'White')
insert into color (idColor, name) values (8, 'Yellow')

/*###############################################################################################################################*/

/*Preencher a tabela Package*/
insert into package (idPackage, name) values (0, 'N/A')
insert into package (idPackage, name) values (1, 'Bag')
insert into package (idPackage, name) values (2, 'Carton')
insert into package (idPackage, name) values (3, 'Each')
insert into package (idPackage, name) values (4, 'Packet')
insert into package (idPackage, name) values (5, 'Pair')

/*###############################################################################################################################*/

/*Preencher a tabela Category*/
insert into category values ('Novelty Shop')
insert into category values ('Gas Station Shop')
insert into category values ('Gift Shop')
insert into category values ('24H Shop')
insert into category values ('Kiosk')

/*###############################################################################################################################*/

/*Preencher a tabela City*/
insert into city
	select 

		[City Key]
		,city
		,case when st is null then 'VI' ELSE st end,[Sales Territory] as st1
		,[Latest Recorded Population]

	from (

	select 
		[City Key],
		city,
	
		case when (select idstate from [State] where [name] =''+ [State Province] COLLATE Latin1_General_100_CI_AS +'') is null then 
	
		( select idstate from [State] where [State Province] like '%'+ [name] COLLATE Latin1_General_100_CI_AS +'%' ) 
	
		else
	
		(select idstate from [State] where [name] =''+ [State Province] COLLATE Latin1_General_100_CI_AS +'') end as st
		, 
		[Sales Territory],
		[Latest Recorded Population]
	
	from wwi_ds.dbo.City  
	) as q

/*###############################################################################################################################*/

/*Preencher a tabela BuyingGroup*/
insert into wwi.dbo.BuyingGroup 
select [Buying Group] from wwi_ds.dbo.Customer
group by [Buying Group]

/*###############################################################################################################################*/

/*Preencher a tabela Person Type com os 2 tipos de pessoas identificadas*/
insert into persontype values ('Employee');
insert into persontype values ('Customer');

/*###############################################################################################################################*/

/*Preencher a tabela Person com os dados dos Employee*/
insert into wwi.dbo.person 
select Employee,[Preferred Name],[Is Salesperson],null,null,Photo,0,1 from WWI_DS.dbo.Employee;

/*###############################################################################################################################*/

/*Preencher a tabela Person com os dados dos Contact Person do customer*/
insert into wwi.dbo.person
select [Primary Contact],[Primary Contact],0,null,null,null,0,2 from WWI_DS.dbo.Customer;

/*###############################################################################################################################*/

/*Preencher a tabela Employee*/
insert into Employee
select idPerson,null,getdate(),null from person where personTypeId=1;

/*###############################################################################################################################*/

/*Atualizações necessárias para se conseguir unificar os dados do customer no passo seguinte*/
update WWI_DS.dbo.Customer set Category = 'Kiosk' where Category='Quiosk';
update WWI_DS.dbo.Customer set Category = 'Gift Shop' where Category='GiftShop';

/*###############################################################################################################################*/

/*Preencher a tabela Customer*/
insert into Customer
select 
		p.idPerson
       ,dsC.Customer
	   ,(select idbuyingGroup from BuyingGroup where name = dsC.[Buying Group] COLLATE Latin1_General_100_CI_AS) as bg
	   ,(select idCategory from Category where [name] = dsC.Category COLLATE Latin1_General_100_CI_AS) as ct
	   ,[Postal Code]
	   ,dsc.[WWI Customer ID]
	   from WWI_DS.dbo.Customer dsC
inner join person p on p.[name] = dsc.[Primary Contact] COLLATE Latin1_General_100_CI_AS
and p.personTypeId=2

/*###############################################################################################################################*/

/*Preencher a tabela Produto*/
insert into wwi.dbo.Product
select 
	 [Stock Item]
	,(select idcolor from wwi.dbo.color where name = si.Color COLLATE Latin1_General_100_CI_AS) 
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
/*Preencher a tabela sale*/
  insert into sale 
  select 
  [sale key],
  [delivery date key],
  (select idEmployee from Employee where idEmployee=[Salesperson Key]),
  Description
  from 
     [WWI_DS].[dbo].[Sale]
	   order by [Sale Key]

/*###############################################################################################################################*/
  /*Preencher a tabela Invoice*/
  insert into WWI.dbo.Invoice
  select 
  [WWI Invoice ID],
 (select idCity from city where idCity=[City Key]),
  [Invoice Date Key]
  from 
     [WWI_DS].[dbo].[Sale]
	 group by [WWI Invoice ID],[City Key], [Invoice Date Key]


/*###############################################################################################################################*/
  /*Preencher a tabela InvoiceCustomer. Esta tabela teve de ser criada porque nos dados fornecidos uma fatura estava associada a 
  mais do que um cliente. Do ponto de vista conceptual esta foi a solução encontrada para contornar o problema.*/
insert into InvoiceCustomer
select 
(select idInvoice from Invoice where idinvoice= [WWI Invoice ID]),
(select idCustomer from Customer where idCustomer=[Customer Key]),
(select idsale from sale where idSale = [Sale Key])
  from 
     [WWI_DS].[dbo].Sale 

/*###############################################################################################################################*/

/*Preencher a tabela saleproduct*/
insert into saleproduct 
	select 
	(select idproduct from Product where idProduct=[Stock Item Key]),
	(select idSale from Sale where idSale=[Sale Key]),
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

