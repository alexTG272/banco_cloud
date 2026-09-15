import {
  AuthenticationDetails,
  CognitoUser,
  CognitoUserPool,
  CognitoUserSession,
} from "amazon-cognito-identity-js";

import { cognitoConfig } from "../config/cognito";

const userPool = new CognitoUserPool({
  UserPoolId: cognitoConfig.userPoolId,
  ClientId: cognitoConfig.clientId,
});

export const iniciarSesion = (
  username: string,
  password: string
): Promise<CognitoUserSession> => {

  return new Promise((resolve, reject) => {

    const authenticationDetails = new AuthenticationDetails({
      Username: username,
      Password: password,
    });

    const cognitoUser = new CognitoUser({
      Username: username,
      Pool: userPool,
    });

    cognitoUser.authenticateUser(authenticationDetails, {
      onSuccess: (session: CognitoUserSession) => {
        resolve(session);
      },

      onFailure: (error) => {
        reject(error);
      },
    });
  });
};

export const cerrarSesion = (): void => {

  const cognitoUser = userPool.getCurrentUser();

  if (cognitoUser) {
    cognitoUser.signOut();
  }
};

export const obtenerUsuarioActual = (): CognitoUser | null => {

  return userPool.getCurrentUser();
};

export const obtenerToken = (): Promise<string | null> => {

  return new Promise((resolve) => {

    const cognitoUser = userPool.getCurrentUser();

    if (!cognitoUser) {
      resolve(null);
      return;
    }

    cognitoUser.getSession(
      (
        error: Error | null,
        session: CognitoUserSession | null
      ) => {

        if (error || !session || !session.isValid()) {
          resolve(null);
          return;
        }

        resolve(
          session.getIdToken().getJwtToken()
        );
      }
    );
  });
};
