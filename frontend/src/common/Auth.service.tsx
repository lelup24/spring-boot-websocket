function decodeJwtToken(token: string): any {
  const base64Url = token.split(".")[1];
  const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  const jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map((c) => {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join(""),
  );

  return JSON.parse(jsonPayload);
}

export class AuthService {
  static date: Date;

  static isAuthenticated(): boolean {
    const token = localStorage.getItem("access-token");
    if (!token) {
      return false;
    }

    AuthService.date = new Date();
    const decodedJwtToken = decodeJwtToken(token);

    return decodedJwtToken["exp"] >= AuthService.date.getTime() / 1000;
  }
}
