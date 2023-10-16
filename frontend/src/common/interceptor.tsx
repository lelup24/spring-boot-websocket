export class Interceptor {
  static init() {
    const { fetch: originalFetch } = window;

    window.fetch = async (...args) => {
      let [resource, config] = args;

      const accessToken = localStorage.getItem("access-token");

      config = {
        ...config,
        headers: {
          "Content-Type": "application/json",
        },
      };

      if (!accessToken) {
        return await originalFetch(resource, config);
      }

      return await originalFetch(resource, {
        ...config,
        headers: {
          ...config.headers,
          Authorization: "Bearer " + accessToken,
        },
      });
    };
  }
}
