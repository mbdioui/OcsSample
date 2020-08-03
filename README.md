#recrutement process
Orange 
Prestations TV 
Exercice de recrutement pour rejoindre le Pôle de développeurs chez OPTV. 
En utilisant la simple API d’OCS, réaliser une petite interface de recherche de programmes.
1. L’interface utilisateur devra proposer un champ de recherche puis devra afficher sous forme de grille les 
différents programmes retournés par la requête API en résultat de recherche.
Le titre ainsi que le sous-titre devront être affichés.
Si une image est disponible pour le programme, elle devra être affichée en utilisant comme host url https://
statics.ocs.fr,
• Ex : http://statics.ocs.fr/data_plateforme/program/43787/
origin_onceuponatiw0156629_09bjd.jpg?size=medium.
https://api.ocs.fr/apps/v2/contents?search=title
Ex d’appel : https://api.ocs.fr/apps/v2/contents?search=title%3DGame => le texte saisi par l’utilisateur est 
« Game »
2. Sur le clic d’un programme, une nouvelle page devra afficher le détail d’un programme avec comme 
contenu :
• L’image en grand format : champ fullscreenimageurl récupéré lors de la recherche (si disponible) en 
utilisant comme host url https://statics.ocs.fr,
• Ex : http://statics.ocs.fr/data_plateforme/program/43787/
origin_onceuponatiw0156629_09bjd.jpg?size=medium
• Un bouton Play sur l’image
• Le titre du programme ou de la série : champ title récupéré lors de la recherche,
• Le sous-titre du programme ou de la série : champ subtitle récupéré lors de la recherche,
• Le pitch du programme ou de la série : champ pitch (de la première saison disponible dans le cas 
d’une série), récupéré via le detaillink (champ récupéré lors de la recherche).
• Utilisation du detaillink : https://api.ocs.fr<detaillink>
	 	 	 Ex : https://api.ocs.fr/apps/v2/details/serie/PSGAMEOFTHRW0058624
3. Le clique sur le bouton Play devra lancer la lecture de la video ci-dessous, dans un player type <AVPlayer | 
ExoPlayer | ShakaPlayer> avec au minimum les fonctionnalités basique d’un player (play | pause | stop) :
• Url stream Dash : https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-
bf1d-4e3d-8899-f0f6155f6efa.mpd
• Documentation ExoPlayer : https://exoplayer.dev
Contraintes :
• Aucune librairie externe ne doit être utilisée (On acceptera quand même pour le networking et le download 
d’image)
• Le projet doit respecter la compatibilité Android 6
• Développement en Kotlin préconisé
• Le code doit être versionné (Git) et fonctionnel sur la branche master
Dans cette exercice, nous serons attentif à :
• la créativité
• la qualité dans le code
• un choix d’architecture modulaire qui respecte le principe de responsabilité unique
• la couverture des tests unitaires
Imaginez votre application comme étant la base d’une application qui va évoluer (nouvelles 
fonctionnalités, nouveaux écrans, nouveaux webservice, …) au sein d’une équipe de plusieurs 
développeurs. 
Bon courage et faites le maximum pour vous démarquer des autres candidats.
Vous disposez d’un délai d’une semaine pour réaliser cette exercice.
Nous avons hâte de voir votre projet !
