package com.example.projets;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;

@ApplicationScoped
@FacesConfig( version = FacesConfig.Version.JSF_2_3 )         // Activation de CDI
public class ApplicationConfiguration {

}
