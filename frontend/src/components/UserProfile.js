var UserProfile = (function() {
    var email = "";
    var tipo = "";
  
    var getEmail = function() {
      return email;    // Or pull this from cookie/localStorage
    };
  
    var setEmail = function(_email) {
      email=_email;    
      // Also set this in cookie/localStorage
    };

    var getTipo = function() {
        return tipo;
    }


    var setTipo = function(_tipo) {
        tipo=_tipo;
    }
  
    return {
      getEmail: getEmail,
      setEmail: setEmail,
      getTipo: getTipo,
      setTipo: setTipo,
    }
  
  })();
  
  export default UserProfile;