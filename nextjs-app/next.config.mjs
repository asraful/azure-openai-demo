/** @type {import('next').NextConfig} */
const nextConfig = {
  output: "standalone",
  reactStrictMode: true,
  images: {
    unoptimized: true,
  },

  //https://github.com/vercel/next.js/discussions/36598
  //https://velog.io/@u0_new/Next.js-Nginx-%ED%94%84%EB%A1%9D%EC%8B%9C-%EC%84%A4%EC%A0%95-%EC%8B%9C-500-Internal-Server-Error-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0
  //https://vercel.com/docs/limits/overview#proxied-request-timeout
  experimental: {
    proxyTimeout: 1000 * 120, // 120 seconds
  },
  async rewrites() {
    return [
      {
        source: "/api/:path*",
        //backend, from nginx configuration
        destination: "http://backend:8080/api/:path*",
      },
    ];
  },
};
export default nextConfig;

//https://github.com/vercel/next.js/discussions/36192
