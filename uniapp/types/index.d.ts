declare module 'uview-plus';

declare interface ImportMetaEnv {
    readonly VITE_BASE_API: string
}

interface ImportMeta {
    readonly env: ImportMetaEnv
}
  