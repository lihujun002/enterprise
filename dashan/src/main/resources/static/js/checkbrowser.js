function check()
{
	if (navigator.userAgent.indexOf('MSIE') >= 0) 
	{
		if (navigator.userAgent.indexOf('Opera') < 0) 
		{
			location.href = "compatible";
		}
	}	
}

check();