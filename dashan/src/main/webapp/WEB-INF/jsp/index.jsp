<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="keywords"
	content="Cultivation Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script>
	if ((navigator.userAgent.indexOf('MSIE') >= 0)
			&& (navigator.userAgent.indexOf('Opera') < 0)) {
		location.href = "compatible.html";
	}
</script>
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<link href='http://fonts.useso.com/css?family=Lobster' rel='stylesheet'
	type='text/css' />
<link
	href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />
<link
	href='http://fonts.useso.com/css?family=Josefin+Sans:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic'
	rel='stylesheet' type='text/css' />
<script src="js/jquery.min.js"></script>

<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1200);
		});
	});
</script>
<!---End-smoth-scrolling---->
<link rel="stylesheet" href="css/swipebox.css" />
<script src="js/jquery.swipebox.min.js"></script>
<script type="text/javascript">
	jQuery(function($) {
		$(".swipebox").swipebox();
	});
</script>
<!--Animation-->
<script src="js/wow.min.js"></script>
<link href="css/animate.css" rel='stylesheet' type='text/css' />
<script>
	new WOW().init();
</script>
<!---/End-Animation---->


</head>
<body>
	<div class="header" id="home">
		<div class="container">
			<div class="header-top">
				<div class="top-menu">
					<span class="menu"><img src="images/nav.png" alt="" /> </span>
					<ul>
						<li><a href="index.html" class="active">首页</a></li>
						<label>/</label>
						<li><a href="#">关于</a></li>
						<label>/</label>
						<li><a href="#">服务</a></li>
						<label>/</label>
						<li><a href="#">产品</a></li>
						<label>/</label>
						<li><a href="#">博客</a></li>
						<label>/</label>
						<li><a href="#">联系我们</a></li>
					</ul>
					<!-- script for menu -->

					<script>
						$("span.menu").click(function() {
							$(".top-menu ul").slideToggle("slow", function() {
							});
						});
					</script>
					<!-- //script for menu -->
				</div>
				<div class="search">
					<form>
						<input type="text" value="Search.." onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = '';}"> <input
							type="submit" value="">
					</form>
				</div>

				<div class="clearfix"></div>

			</div>
			<div class="logo wow bounceIn animated" data-wow-delay="0.4s"
				style="visibility: visible; -webkit-animation-delay: 0.4s;">
				<a href="index.html">竹..酒</a>
			</div>
			<div class="header-bottom">
				<div class="header-grids">
					<div class="col-md-3 header-grid">
						<div class="header-img1 wow bounceInLeft animated"
							data-wow-delay="0.4s"
							style="visibility: visible; -webkit-animation-delay: 0.4s;">
							<img src="images/icon4.png" class="img-responsive" alt="/">
							<h4>鲜竹酒</h4>
							<p>竹筒酒是一种鲜竹酒，用竹筒装着种出来的，成酒色泽金黄，酒质芬香浓郁，醇和甘爽，
								余味悠长的原生态高端食品，并且纯绿色零污染的</p>
						</div>
					</div>
					<div class="col-md-3 header-grid">
						<div class="header-img2 wow fadeInDownBig animated animated"
							data-wow-delay="0.4s">
							<img src="images/icon5.png" class="img-responsive" alt="/">
							<h4>做法</h4>
							<p>竹筒酒是在竹子还是竹笋幼苗时直接注入了上等的糯米白酒原浆，经过三年时间的自然酝酿和吸收天然露水精华，才能酝酿出现在的竹子林天露酒
							</p>
						</div>
					</div>

					<div class="col-md-3 header-grid">
						<div class="header-img3 wow fadeInUpBig animated animated"
							data-wow-delay="0.4s">
							<img src="images/icon6.png" class="img-responsive" alt="/">
							<h4>功效</h4>
							<p>竹筒酒适量饮之，有舒筋活络，提高免疫力，软化血管，抗衰老，延年益寿之功效
								亲朋聚会，击竹共饮，让人有翠竹林中曲水流觞的惬意和快感。</p>
						</div>
					</div>
					<div class="col-md-3 header-grid">
						<div class="header-img4 wow bounceInRight animated"
							data-wow-delay="0.4s"
							style="visibility: visible; -webkit-animation-delay: 0.4s;">
							<img src="images/icon7.png" class="img-responsive" alt="/">
							<h4>生产过程</h4>
							<p>竹子林天露选用新鲜的野竹笋，运用独创技术，将优质糯米白酒浆注入活竹笋内，自然生长，天然密封，饱吸竹子精华
								，酿成人间美酒.</p>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="about-section">
			<div class="container">
				<h3>关于我们</h3>
				<div class="about-grids">
					<div class="col-md-5 about-img wow bounceInRight animated"
						data-wow-delay="0.4s"
						style="visibility: visible; -webkit-animation-delay: 0.4s;">
						<img src="images/868315661000290995.jpg" class="img-responsive"
							alt="/">
					</div>
					<div class="col-md-7 about-grid wow bounceInLeft animated"
						data-wow-delay="0.4s"
						style="visibility: visible; -webkit-animation-delay: 0.4s;">
						<p>资兴市大山农业开发有限公司是一家集生态农业技术研究、研发，毛竹、茶叶、谷物、蔬菜、水果、畜牧、竹酒等农产品的种植、研制、销售、(含互联网销售);
							农产品初加工服务；农业生态旅游开发；农副产品、工艺品批发、零售(含互联网销售)；以及旅游相关行业的服务行业；农村基础建设。</p>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="service-section">
			<div class="container">
				<h3>我们提供的服务</h3>
				<div class="service-grids">
					<div class="col-md-4 service-grid wow bounceInLeft animated"
						data-wow-delay="0.4s"
						style="visibility: visible; -webkit-animation-delay: 0.4s;">
						<img src="images/icon1.png" class="img-responsive" alt="/">
						<h4>农业发展</h4>
						<p>Cras consequat iaculis lorem, id vehicula erat mattis quis.
							Vivamus laoreet velit justo, in ven e natis purus.Praesent nec
							sagittis mauris. Fusce convallis nunc neque.Integer
							egestas.Vivamus laoreet velit justo</p>
					</div>
					<div
						class="col-md-4 service-grid wow fadeInUpBig animated animated"
						data-wow-delay="0.4s">
						<img src="images/icon2.png" class="img-responsive" alt="/">
						<h4>竹酒养生</h4>
						<p>Cras consequat iaculis lorem, id vehicula erat mattis quis.
							Vivamus laoreet velit justo, in ven e natis purus.Praesent nec
							sagittis mauris. Fusce convallis nunc neque.Integer
							egestas.Vivamus laoreet velit justo</p>
					</div>
					<div class="col-md-4 service-grid wow bounceInRight animated"
						data-wow-delay="0.4s"
						style="visibility: visible; -webkit-animation-delay: 0.4s;">
						<img src="images/icon3.png" class="img-responsive" alt="/">
						<h4>生态旅游</h4>
						<p>Cras consequat iaculis lorem, id vehicula erat mattis quis.
							Vivamus laoreet velit justo, in ven e natis purus.Praesent nec
							sagittis mauris. Fusce convallis nunc neque.Integer
							egestas.Vivamus laoreet velit justo</p>
						`
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="work-section wow bounceIn animated" data-wow-delay="0.4s"
			style="visibility: visible; -webkit-animation-delay: 0.4s;">
			<div class="container">
				<h3>我们的最新产品</h3>
				<div class="latest-grids">
					<div class="latest-grid1">
						<div class="col-md-3 latest-grid work">
							<a href="images/429746738280352018.jpg" class="swipebox"><img
								src="images/560445354179892346.jpg" class="img-responsive"
								alt="/">
								<div class="textbox">
									<img src="images/magnify.png" class="img-responsive" alt="/">
								</div> </a>
						</div>

						<div class="col-md-3 latest-grid work">
							<a href="images/429746738280352018.jpg" class="swipebox"><img
								src="images/429746738280352018.jpg" class="img-responsive"
								alt="/">
								<div class="textbox">
									<img src="images/magnify.png" class="img-responsive" alt="/">
								</div>
						</div>
						</a>
						<div class="col-md-3 latest-grid work">
							<a href="images/560445354179892346.jpg" class="swipebox"><img
								src="images/560445354179892346.jpg" class="img-responsive"
								alt="/">
								<div class="textbox">
									<img src="images/magnify.png" class="img-responsive" alt="/">
								</div>
						</div>
						</a>
						<div class="col-md-3 latest-grid work">
							<a href="images/560445354179892346.jpg" class="swipebox"><img
								src="images/560445354179892346.jpg" class="img-responsive"
								alt="/">
								<div class="textbox">
									<img src="images/magnify.png" class="img-responsive" alt="/">
								</div>
						</div>
						</a>
						<div class="clearfix"></div>
					</div>
					<div class="latest-grid2">
						<div class="col-md-3 latest-grid work">
							<a href="images/20151125172932.jpg" class="swipebox"><img
								src="images/429746738280352018.jpg" class="img-responsive"
								alt="/">
								<div class="textbox">
									<img src="images/magnify.png" class="img-responsive" alt="/">
								</div>
						</div>
						</a>
						<div class="col-md-3 latest-grid work">
							<a href="images/429746738280352018.jpg" class="swipebox"><img
								src="images/429746738280352018.jpg" class="img-responsive"
								alt="/">
								<div class="textbox">
									<img src="images/magnify.png" class="img-responsive" alt="/">
								</div>
						</div>
						</a>
						<div class="col-md-3 latest-grid work">
							<a href="images/20151125172932.jpg" class="swipebox"><img
								src="images/429746738280352018.jpg" class="img-responsive"
								alt="/">
								<div class="textbox">
									<img src="images/magnify.png" class="img-responsive" alt="/">
								</div>
						</div>
						</a>
						<div class="col-md-3 latest-grid work">
							<a href="images/560445354179892346.jpg" class="swipebox"><img
								src="images/20151125172932.jpg" class="img-responsive" alt="/">
								<div class="textbox">
									<img src="images/magnify.png" class="img-responsive" alt="/">
								</div>
						</div>
						</a>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="news-section">
			<div class="container">
				<h3>最新资讯</h3>
				<div class="news-grids">
					<div class="col-md-4 news-grid wow bounceInLeft animated"
						data-wow-delay="0.4s"
						style="visibility: visible; -webkit-animation-delay: 0.4s;">
						<img src="images/20151125172932.jpg" class="img-responsive"
							alt="/">
						<h4>
							<a href="#">豪华版</a>
						</h4>
						<p class="date">
							March 23rd, 2015 <a href="#">5 Comments</a>
						</p>
						<p>Praesent vestim molestie lacus. Aenean nonummy hendrerit
							mauris. Phasellus porta. Fusce suscipit varius mi. Cum sociis
							natoque penatibus et magnis dis parturient montes, nascetur
							ridiculus mus.</p>
						<a href="#" class="button hvr-shutter-in-vertical">read more</a>
					</div>
					<div
						class="col-md-4 news-grid wow fadeInDown Big animated animated"
						data-wow-delay="0.4s">
						<img src="images/20151125172943.jpg" class="img-responsive"
							alt="/">
						<h4>
							<a href="#">钻石版</a>
						</h4>
						<p class="date">
							March 23rd, 2014 <a href="#">5 Comments</a>
						</p>
						<p>Praesent vestim molestie lacus. Aenean nonummy hendrerit
							mauris. Phasellus porta. Fusce suscipit varius mi. Cum sociis
							natoque penatibus et magnis dis parturient montes, nascetur
							ridiculus mus.</p>
						<a href="#" class="button hvr-shutter-in-vertical">read more</a>
					</div>
					<div class="col-md-4 news-grid wow bounceInRight animated"
						data-wow-delay="0.4s"
						style="visibility: visible; -webkit-animation-delay: 0.4s;">
						<img src="images/20151125172932.jpg" class="img-responsive"
							alt="/">
						<h4>
							<a href="#">终极版</a>
						</h4>
						<p class="date">
							March 25rd, 2014 <a href="#">5 Comments</a>
						</p>
						<p>Praesent vestim molestie lacus. Aenean nonummy hendrerit
							mauris. Phasellus porta. Fusce suscipit varius mi. Cum sociis
							natoque penatibus et magnis dis parturient montes, nascetur
							ridiculus mus.</p>
						<a href="#" class="button hvr-shutter-in-vertical">read more</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="categories-section">
		<div class="container">
			<div class="footer-grids">
				<div class="col-md-4 up wow bounceInLeft animated"
					data-wow-delay="0.4s"
					style="visibility: visible; -webkit-animation-delay: 0.4s;">
					<h3>公司高管</h3>
					<div class="up1">
						<div class="up-img">
							<img src="images/20151125174002.jpg">
						</div>
						<div class="up-text">
							<a href="#">张小帅</a>
							<p>帅气逼人.</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="up1">
						<div class="up-img">
							<img src="images/20151125174002.jpg">
						</div>
						<div class="up-text">
							<a href="#">王小帅</a>
							<p>帅得掉渣.</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="up1">
						<div class="up-img">
							<img src="images/20151125174002.jpg">
						</div>
						<div class="up-text">
							<a href="#">刘老帅</a>
							<p>一帅到底.</p>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-md-4 cat wow bounceIn animated"
					data-wow-delay="0.4s"
					style="visibility: visible; -webkit-animation-delay: 0.4s;">
					<h3>类别</h3>
					<ul>
						<li>经典窖藏-大山竹酒.</li>
						<li>精装版-大山竹酒</li>
						<li>豪华版-大山竹酒</li>
						<li>钻石版-大山竹酒</li>
						<li>20年陈酿-大山竹酒</li>
						<li>50年陈酿-大山竹酒</li>
						<li>80年陈酿-大山竹酒</li>
					</ul>
				</div>
				<div class="col-md-4 cont wow bounceInRight animated"
					data-wow-delay="0.4s"
					style="visibility: visible; -webkit-animation-delay: 0.4s;">
					<h3>联系我们</h3>
					<ul>
						<li><i class="phone"></i></li>
						<li><p>1-000-000-0000</p>
							<p>1-000-000-0000</p></li>
					</ul>
					<ul>
						<li><i class="smartphone"></i></li>
						<li><p>李先生</p>
							<p>张先生</p></li>
					</ul>
					<ul>
						<li><i class="message"></i></li>
						<li><a href="mailto:example@mail.com">bcdefg@hijs.dfh</a> <a
							href="mailto:example@mail.com">fjashfaf@jkfs.ckd</a></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="footer-section">
		<div class="container">
			<div class="footer-top">
				<div class="social-icons wow bounceInLeft animated"
					data-wow-delay="0.4s"
					style="visibility: visible; -webkit-animation-delay: 0.4s;">
					<a href="#"><i class="icon1"></i></a> <a href="#"><i
						class="icon2"></i></a> <a href="#"><i class="icon3"></i></a> <a
						href="#"><i class="icon4"></i></a>
				</div>
			</div>
			<div class="footer-middle wow fadeInDown Big animated animated"
				data-wow-delay="0.4s">
				<div class="bottom-menu">
					<ul>
						<li><a href="#index.html">home</a></li>
						<li><a href="#about.html">About</a></li>
						<li><a href="#services.html">Services</a></li>
						<li><a href="#products.html">products</a></li>
						<li><a href="#blog.html">blog</a></li>
						<li><a href="#contact.html">Contact</a></li>
					</ul>
				</div>
			</div>
			<div class="footer-bottom wow bounceInRight animated"
				data-wow-delay="0.4s"
				style="visibility: visible; -webkit-animation-delay: 0.4s;">
				<p>Copyright &copy; 2015.大山农业发展有限公司 版本所有.</p>
			</div>
			<script type="text/javascript">
				$(document).ready(function() {
					/*
					var defaults = {
						containerID: 'toTop', // fading element id
						containerHoverID: 'toTopHover', // fading element hover id
						scrollSpeed: 1200,
						easingType: 'linear' 
					};
					 */

					$().UItoTop({
						easingType : 'easeOutQuart'
					});

				});
			</script>
			<a href="#" id="toTop" style="display: block;"> <span
				id="toTopHover" style="opacity: 1;"> </span></a>
		</div>
	</div>

</body>
</html>
